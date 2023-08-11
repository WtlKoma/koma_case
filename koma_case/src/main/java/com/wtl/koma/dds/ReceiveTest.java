package com.wtl.koma.dds;

import DDS.DomainParticipantFactory;
import FTrans.FTransAdaptor_Java;
import FTrans.FTransListenerBase;
import OpenDDS.DCPS.TheParticipantFactory;
import org.omg.CORBA.StringSeqHolder;

/**
 *
 */
public class ReceiveTest {

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


        FTransListenerBase listern = new FTransListenerBase();
        inst.SubscribeFile("DCRS_000000001", "./receiveDDS", listern);

        while (true) {
            Thread.sleep(1000);
            System.out.println("1111");
        }
    }


}
