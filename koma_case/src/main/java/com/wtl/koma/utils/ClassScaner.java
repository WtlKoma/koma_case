package com.wtl.koma.utils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.SystemPropertyUtils;

/**
 * <p>
 * Description:
 * </p>
 * 
 * @author WTL
 * @date 2019年7月31日
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ClassScaner implements ResourceLoaderAware {
	
	private final List<TypeFilter> includeFilters = new LinkedList<TypeFilter>();
	private final List<TypeFilter> excludeFilters = new LinkedList<TypeFilter>();

	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

	private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
	
	public static Set<Class<?>> scan(String[] basePackages, Class<? extends Annotation>... annotations){
		ClassScaner cs = new ClassScaner();
		
		if (ArrayUtils.isNotEmpty(annotations)) {
			for (Class anno : annotations) {
				cs.addIncludeFilter(new AnnotationTypeFilter(anno));
			}
		}
		
		Set<Class<?>> classes = new HashSet<Class<?>>();
		for (String s : basePackages) {
			classes.addAll(cs.doScan(s));
		}
		
		return classes;
	}
	
	public static Set<Class<?>> scan(String basePackages, Class<? extends Annotation> annotations){
		return ClassScaner.scan(StringUtils.tokenizeToStringArray(basePackages, ",;\t\n"), annotations);
	}
	
	public final ResourceLoader getResourceLoader(){
		return this.resourcePatternResolver;
	}
	
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
		this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
	}
	
	public void addIncludeFilter(TypeFilter includeFilter){
		this.includeFilters.add(includeFilter);
	}
	public void addExcludeFilter(TypeFilter excludeFilter){
		this.includeFilters.add(0, excludeFilter);
	}
	
	public void resetFilters(Boolean useDefaultFilters){
		this.includeFilters.clear();
		this.excludeFilters.clear();
	}

	public Set<Class<?>> doScan(String basePackage) {
		
		Set<Class<?>> classes = new HashSet<Class<?>>();
		
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				+ ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage))
				+ "/**/*.class";
		try {
			Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
			
			Resource resource = null;
			MetadataReader metadataReader = null;
			for (int i = 0; i < resources.length; i++) {
				resource = resources[i];
				if (resource.isReadable()) {
					metadataReader = metadataReaderFactory.getMetadataReader(resource);
					if ((includeFilters.size() == 0 && excludeFilters.size() == 0) || matches(metadataReader)) {
						try {
							classes.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return classes;
	}
	
	//不明白做什么用的
	private Boolean matches(MetadataReader metadataReader) throws IOException{
		for (TypeFilter tf : excludeFilters) {
			if (tf.match(metadataReader, metadataReaderFactory)) {
				return false;
			}
		}
		
		for (TypeFilter tf : includeFilters) {
			if (tf.match(metadataReader, metadataReaderFactory)) {
				return true;
			}
		}
		
		return false;
	}

}
