package com.wtl.koma.dds;

import FTrans.FTransListenerBase;

public class FTransListener extends FTransListenerBase {

    public void FTrans_Detail(String TransTopic,
                              String TransID,
                              String FilePath,
                              long TotalFSize,
                              long FOffset,
                              long DSize,
                              long FTransStatus,
                              long ErrCode)
    {
    		System.out.println("JAVA FTrans_Detail TransTopic   = " + TransTopic); // 文件发送主题
    		System.out.println("JAVA FTrans_Detail TransID      = " + TransID ); // 文件发送ID
    		System.out.println("JAVA FTrans_Detail FilePath     = " + FilePath); // 文件存储/发送路径
    		System.out.println("JAVA FTrans_Detail TotalFSize   = " + TotalFSize); // 文件总大小
    		System.out.println("JAVA FTrans_Detail FOffset      = " + FOffset); // 文件已接收/发送的大小
    		System.out.println("JAVA FTrans_Detail DSize        = " + DSize); // 本次接收/发送数据包大小
    		System.out.println("JAVA FTrans_Detail FTransStatus = " + FTransStatus); // 0文件成功 1文件失败 2文件未完成
    		System.out.println("JAVA FTrans_Detail ErrCode      = " + ErrCode); // 0为成功，其他为失败
    	
        if(TotalFSize == (FOffset + DSize))
        {

        }
    }
}
