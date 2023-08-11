package com.wtl.koma.dds;

import DDS.DomainParticipantFactory;
import FTrans.FTransAdaptor_Java;
import FTrans.FTransResult;
import OpenDDS.DCPS.TheParticipantFactory;
import org.omg.CORBA.StringSeqHolder;

import java.util.ArrayList;

/**
 *
 */
public class SendTest {

    public static void main(String[] args) throws InterruptedException {

        DomainParticipantFactory dpf = TheParticipantFactory.WithArgs(new StringSeqHolder(args));
        if (dpf == null) {
            return;
        }

        FTransAdaptor_Java inst = FTransAdaptor_Java.Instance();
        if(inst == null)
        {
            return;
        }

        inst.Init("DCRS_000000001");

        ArrayList<FTransResult> Results = new ArrayList<FTransResult>();
        String TransID = inst.MakeTransID();
		FTransListener listern = new FTransListener();
        long pubret = inst.PublishFile(Results,"DCRS_000000001",TransID,"D:/DDS/test.pdf", "",1,30,false,listern);
        System.out.println(pubret);
        inst.CleanUp();
    }



}
