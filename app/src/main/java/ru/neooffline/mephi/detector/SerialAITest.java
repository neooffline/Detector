package ru.neooffline.mephi.detector;

import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.util.SerialParameters;

/**
 * Created by user on 16.03.2018.
 */

public class SerialAITest {
    public static void main(String[] args) throws Exception {
        SerialConnection connection = null;
        ModbusSerialTransaction transaction = null;
        ReadInputRegistersRequest registersRequest = null;
        ReadInputRegistersResponse registersResponse = null;

        String portname = null;
        int unitid = 0;
        int ref = 0;
        int count = 0;
        int repeat = 1;
        if (args.length < 4) {
            System.exit(1);
        } else {
            try {
                portname = args[0];
                unitid = Integer.parseInt(args[1]);
                ref = Integer.parseInt(args[2]);
                count = Integer.parseInt(args[3]);
                if (args.length == 5) repeat = Integer.parseInt(args[4]);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
            }
        }
//        ModbusCoupler.class(null);
        ModbusCoupler.getReference().setUnitID(1);
        SerialParameters parameters = new SerialParameters();
        parameters.setPortName(portname);
        parameters.setBaudRate(34800);
        parameters.setDatabits(8);
        parameters.setParity("None");
        parameters.setStopbits(1);
        parameters.setEncoding("ascii");
        parameters.setEcho(false);
        connection = new SerialConnection(parameters);
        connection.open();
        registersRequest = new ReadInputRegistersRequest(ref, count);
        registersRequest.setUnitID(unitid);
        registersRequest.setHeadless();
        transaction = new ModbusSerialTransaction(connection);
        transaction.setRequest(registersRequest);
        int k = 0;
        do {
            transaction.execute();
            registersResponse = (ReadInputRegistersResponse) transaction.getResponse();
            for (int i = 0; i < registersResponse.getWordCount(); i++) {
                System.out.println("Word" + i + "=" + registersResponse.getRegisterValue(i));
                return registersResponse.getRegisterValue(i);
            }
            k++;
        } while (k < repeat);
        connection.close();
    }
}
