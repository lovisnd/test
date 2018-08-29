package com.tiankui.reactService.net;

import org.apache.log4j.Logger;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.Null;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * SNMP基础工具类
 */
public class SnmpUtils {
    private static Logger logger = Logger.getLogger(SnmpUtils.class);

    //设置snmp的版本
    public static final int DEFAULT_VERSION = SnmpConstants.version2c;
    //设置传输协议
    public static final String DEFAULT_PROTOCOL = "udp";
    //设置snmp端口号
    public static final int DEFAULT_PORT = 161;
    //设置超时时间(单位:毫秒)
    public static final long DEFAULT_TIMEOUT = 3 * 1000L;
    //设置重试次数
    public static final int DEFAULT_RETRY = 3;

    /**
     * 创建对象communityTarget,用于返回target
     *
     * @param ip
     * @param community
     * @return
     */
    public static CommunityTarget createDefault(String ip, String community) {
        Address address = GenericAddress.parse(DEFAULT_PROTOCOL + ":" + ip + "/" + DEFAULT_PORT);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(community));
        target.setAddress(address);
        target.setVersion(DEFAULT_VERSION);
        target.setTimeout(DEFAULT_TIMEOUT);
        target.setRetries(DEFAULT_RETRY);
        return target;
    }

    /**
     * 协议传输完后关闭协议
     *
     * @param snmp
     */
    private static void closeSnmp(Snmp snmp) {
        if (snmp != null) {
            try {
                snmp.close();
            } catch (IOException e) {
                snmp = null;
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据OID，获取单条消息
     *
     * @param ip
     * @param community
     * @param oid
     */
    @SuppressWarnings("finally")
	public String snmpGet(String ip, String community, String oid) {
        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        String vb = null;

        try {
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oid)));
            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            logger.debug("\n********** 发送PDU **********\n");
            pdu.setType(PDU.GET);
            ResponseEvent responseEvent = snmp.send(pdu, target);
            //打印ip地址
            logger.debug("***** PeerAddress:" + responseEvent.getPeerAddress() + " *****");
            PDU response = responseEvent.getResponse();

            if (response == null) {
                logger.error("***** response is null or request time out! *****");
            } else {
                logger.debug("***** response pdu size is " + response.size() + " *****");
                for (int i = 0; i < response.size(); i++) {
                    VariableBinding v = response.get(i);
                    vb = v.getVariable().toString();
                    logger.debug("***** " + v.getOid() + "=" + v.getVariable() + " *****");
                }
            }
            logger.debug("\n***** 根据OID，获取单条消息完成! *****\n");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("\n***** SNMP Get Exception:" + e + " *****\n");
        } finally {
            closeSnmp(snmp);
            return vb;
        }
    }

    /**
     * 根据OID列表，一次获取多条OID数据，并且以List形式返回
     *
     * @param ip
     * @param community
     * @param oidList
     */
    @SuppressWarnings("finally")
	public List<VariableBinding> snmpGetList(String ip, String community, List<String> oidList) {
        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        List<VariableBinding> list = new ArrayList<VariableBinding>();
        try {
            PDU pdu = new PDU();

            for (String oid : oidList) {
                pdu.add(new VariableBinding(new OID(oid)));
            }

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            logger.debug("\n********** 发送PDU **********\n");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            logger.debug("***** PeerAddress:" + respEvent.getPeerAddress() + " *****");
            PDU response = respEvent.getResponse();

            if (response == null) {
                logger.error("***** response is null or request time out *****");
            } else {
                logger.debug("***** response pdu size is " + response.size() + " *****");
                for (int i = 0; i < response.size(); i++) {
                    VariableBinding vb = response.get(i);
                    list.add(vb);
                    logger.debug("***** " + vb.getOid() + " = " + vb.getVariable() + " *****");
                }
            }
            logger.debug("\n***** 根据OID列表，一次获取多条OID数据，并且以List形式返回完成 ! *****\n");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("\n***** SNMP Get Exception:" + e + " *****\n");
        } finally {
            closeSnmp(snmp);
            return list;
        }
    }

    /**
     * 根据OID列表，采用异步方式一次获取多条OID数据，并且以List形式返回
     *
     * @param ip
     * @param community
     * @param oidList
     */
    @SuppressWarnings("finally")
	public List<VariableBinding> snmpAsynGetList(String ip, String community, List<String> oidList) {
        CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        final List<VariableBinding> list = new ArrayList<VariableBinding>();
        try {
            PDU pdu = new PDU();

            for (String oid : oidList) {
                pdu.add(new VariableBinding(new OID(oid)));
            }

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            logger.debug("********** 发送PDU **********");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            logger.debug("***** PeerAddress:" + respEvent.getPeerAddress() + " *****");

            /*异步获取*/
            final CountDownLatch latch = new CountDownLatch(1);
            ResponseListener listener = new ResponseListener() {
                public void onResponse(ResponseEvent event) {
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);
                    PDU response = event.getResponse();
                    PDU request = event.getRequest();
                    logger.debug("***** [request]:" + request + " *****");
                    if (response == null) {
                        logger.error("***** [ERROR]: response is null *****");
                    } else if (response.getErrorStatus() != 0) {
                        logger.error("***** [ERROR]: response status " + response.getErrorStatus() + " Text:" + response.getErrorStatusText() + " *****");
                    } else {
                        logger.debug("***** Received response Success! *****");
                        for (int i = 0; i < response.size(); i++) {
                            VariableBinding vb = response.get(i);
                            list.add(vb);
                            logger.debug("***** " + vb.getOid() + " = "
                                    + vb.getVariable() + " *****");
                        }
                        logger.debug("***** SNMP Asyn GetList OID finished. *****");
                        latch.countDown();
                    }
                }
            };

            pdu.setType(PDU.GET);
            snmp.send(pdu, target, null, listener);
            logger.debug("***** asyn send pdu wait for response... *****");

            boolean wait = latch.await(30, TimeUnit.SECONDS);
            logger.debug("***** latch.await =:" + wait + " *****");

            snmp.close();

            logger.debug("***** SNMP GET one OID value finished ! *****");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("***** SNMP Get Exception:" + e + " *****");
        } finally {
            closeSnmp(snmp);
            return list;
        }
    }

    /**
     * 根据targetOID，获取树形数据
     *
     * @param ip
     * @param community
     * @param targetOid
     */
    @SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	public ArrayList<String> snmpWalk(String ip, String community, String targetOid) {
        CommunityTarget target = createDefault(ip, community);
        TransportMapping transport = null;
        ArrayList<String> list = new ArrayList<String>();
        Snmp snmp = null;
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();

            PDU pdu = new PDU();
            OID targetOID = new OID(targetOid);
            pdu.add(new VariableBinding(targetOID));

            boolean finished = false;
            logger.debug("********** SNMP walk start **********");
            while (!finished) {
                VariableBinding vb = null;
                ResponseEvent respEvent = snmp.getNext(pdu, target);

                PDU response = respEvent.getResponse();

                if (null == response) {
                    logger.error("***** responsePDU == null *****");
                    finished = true;
                    break;
                } else {
                    vb = response.get(0);
                }
                finished = checkWalkFinished(targetOID, pdu, vb);
                if (!finished) {
                    logger.debug("***** walk each vlaue :");
                    logger.debug(vb.getOid() + " = " + vb.getVariable());
                    logger.debug("*****");

                    pdu.setRequestID(new Integer32(0));
                    pdu.set(0, vb);
                } else {
                    logger.debug("***** SNMP walk OID has finished. *****");
                    snmp.close();
                }
                list.add(vb.getVariable().toString());
            }
            logger.debug("********** SNMP walk end **********");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("***** SNMP walk Exception: " + e + " *****");
        } finally {
            closeSnmp(snmp);
            return list;
        }
    }

    private static boolean checkWalkFinished(OID targetOID, PDU pdu,
                                             VariableBinding vb) {
        boolean finished = false;
        if (pdu.getErrorStatus() != 0) {
            logger.debug("***** [true] responsePDU.getErrorStatus() != 0 *****");
            logger.debug("***** " + pdu.getErrorStatusText() + " *****");
            finished = true;
        } else if (vb.getOid() == null) {
            logger.debug("***** [true] vb.getOid() == null *****");
            finished = true;
        } else if (vb.getOid().size() < targetOID.size()) {
            logger.debug("***** [true] vb.getOid().size() < targetOID.size() *****");
            finished = true;
        } else if (targetOID.leftMostCompare(targetOID.size(), vb.getOid()) != 0) {
            logger.debug("***** [true] targetOID.leftMostCompare() != 0 *****");
            finished = true;
        } else if (Null.isExceptionSyntax(vb.getVariable().getSyntax())) {
            logger.debug("***** [true] Null.isExceptionSyntax(vb.getVariable().getSyntax()) *****");
            finished = true;
        } else if (vb.getOid().compareTo(targetOID) <= 0) {
            logger.debug("***** [true] Variable received is not lexicographic successor of requested one: " + vb.toString() + " <= " + targetOID + " *****");
            finished = true;
        }
        return finished;
    }

    /*根据targetOID，异步获取树形数据*/
    public void snmpAsynWalk(String ip, String community, String oid) {
        final CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        try {
            logger.debug("********** SNMP walk start **********");

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();

            final PDU pdu = new PDU();
            final OID targetOID = new OID(oid);
            final CountDownLatch latch = new CountDownLatch(1);
            pdu.add(new VariableBinding(targetOID));

            ResponseListener listener = new ResponseListener() {
                public void onResponse(ResponseEvent event) {
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);

                    try {
                        PDU response = event.getResponse();
                        PDU request = event.getRequest();
                        logger.debug("[request]:" + request);
                        if (response == null) {
                            logger.error("***** [ERROR]: response is null *****");
                        } else if (response.getErrorStatus() != 0) {
                            logger.error("***** [ERROR]: response status" + response.getErrorStatus() + " Text:" + response.getErrorStatusText() + " *****");
                        } else {
                            logger.debug("***** Received Walk response value :");
                            VariableBinding vb = response.get(0);

                            boolean finished = checkWalkFinished(targetOID,
                                    pdu, vb);
                            if (!finished) {
                                logger.debug(vb.getOid() + " = "
                                        + vb.getVariable());
                                pdu.setRequestID(new Integer32(0));
                                pdu.set(0, vb);
                                ((Snmp) event.getSource()).getNext(pdu, target,
                                        null, this);
                            } else {
                                logger.debug("***** SNMP Asyn walk OID value success ! *****");
                                latch.countDown();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        latch.countDown();
                    }

                }
            };

            snmp.getNext(pdu, target, null, listener);
            logger.debug("pdu 已发送,等到异步处理结果...");

            boolean wait = latch.await(30, TimeUnit.SECONDS);
            logger.debug("latch.await =:" + wait);
            snmp.close();

            logger.debug("********** SNMP walk end **********");
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("***** SNMP Asyn Walk Exception:" + e + " *****");
        }
    }
}
