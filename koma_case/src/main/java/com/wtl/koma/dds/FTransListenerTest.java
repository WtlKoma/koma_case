package com.wtl.koma.dds;

import FTrans.FTransListenerBase;

public class FTransListenerTest extends FTransListenerBase {

    public void FTrans_Detail(String TransTopic,
                              String TransID,
                              String FilePath,
                              long TotalFSize,
                              long FOffset,
                              long DSize,
                              long FTransStatus,
                              long ErrCode)
    {
    		System.out.println("JAVA FTrans_Detail TransTopic   = " + TransTopic);
    		System.out.println("JAVA FTrans_Detail TransID      = " + TransID );
    		System.out.println("JAVA FTrans_Detail FilePath     = " + FilePath);
    		System.out.println("JAVA FTrans_Detail TotalFSize   = " + TotalFSize);
    		System.out.println("JAVA FTrans_Detail FOffset      = " + FOffset);
    		System.out.println("JAVA FTrans_Detail DSize        = " + DSize);
    		System.out.println("JAVA FTrans_Detail FTransStatus = " + FTransStatus);
    		System.out.println("JAVA FTrans_Detail ErrCode      = " + ErrCode);
    	
        if(TotalFSize == (FOffset + DSize))
        {            
        }
    }
}
