package ru.neooffline.mephi.detector;
import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.serial.SerialParameters;
import com.intelligt.modbus.jlibmodbus.serial.SerialPort;
import jssc.SerialPortList;
/**
 * Created by user on 19.03.2018.
 */

public class SimpleMater {
        public SimpleMater() {
            SerialParameters sp = new SerialParameters();
            Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);
            try {
                // you can use just string to get connection with remote slave,
                // but you can also get a list of all serial ports available at your system.
                String[] dev_list = SerialPortList.getPortNames();
                // if there is at least one serial port at your system
                if (dev_list.length > 0) {
                    // you can choose the one of those you need
                    sp.setDevice(dev_list[0]);
                    // these parameters are set by default
                    sp.setBaudRate(SerialPort.BaudRate.BAUD_RATE_38400);
                    sp.setDataBits(8);
                    sp.setParity(SerialPort.Parity.NONE);
                    sp.setStopBits(1);
                    ModbusMaster m = ModbusMasterFactory.createModbusMasterRTU(sp);
                    m.connect();
                    int slaveId = 1;
                    int offset = 0;
                    int quantity = 1;
                    //you can invoke #connect method manually, otherwise it'll be invoked automatically
                    try {
                        // at next string we receive ten registers from a slave with id of 1 at offset of 0.
                        int[] registerValues = m.readHoldingRegisters(slaveId, offset, quantity);
                        // print values
                        for (int value : registerValues) {
                            System.out.println("Address: " + offset++ + ", Value: " + value);
                        }
                    } catch (RuntimeException e) {
                        throw e;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            m.disconnect();
                        } catch (ModbusIOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
