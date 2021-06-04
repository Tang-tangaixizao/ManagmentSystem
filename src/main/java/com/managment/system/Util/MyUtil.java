package com.managment.system.Util;

import com.managment.system.Entity.DiskInfo;
import com.managment.system.Entity.MyNetInfo;
import org.hyperic.sigar.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class MyUtil {

    /**获取内存信息
     * */
    public static long memory() throws SigarException {
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
        return mem.getUsed() / 1024L/1024L;
    }

    /**获取CPU信息
     * */
    public static double  cpu() throws SigarException {
        Sigar sigar = new Sigar();
        CpuInfo infos[] = sigar.getCpuInfoList();
        CpuPerc cpuList[] = null;
        cpuList = sigar.getCpuPercList();
        for (int i = 0; i < infos.length; i++) {
            return printCpuPerc(cpuList[i]);
        }
        return 0;
    }

    public static double printCpuPerc(CpuPerc cpu) {
        double sum=0;
        sum+=cpu.getCombined();
        return sum;
    }

    /**获取系统文件系统信息
     * */
    public static Map<String, Object> file() throws Exception {
        Map<String,Object> map=new HashMap<>();
        Sigar sigar = new Sigar();
        FileSystem fslist[] = sigar.getFileSystemList();
        long sum=0;
        long surplusSize=0;
        for (int i = 0; i < fslist.length; i++) {
            //System.out.println("分区的盘符名称" + i);
            FileSystem fs = fslist[i];
            FileSystemUsage usage = null;
            usage = sigar.getFileSystemUsage(fs.getDirName());
            switch (fs.getType()) {
                case 0: // TYPE_UNKNOWN ：未知
                    break;
                case 1: // TYPE_NONE
                    break;
                case 2: // TYPE_LOCAL_DISK : 本地硬盘
                    // 文件系统已使用总大小
                    sum+= usage.getUsed()/1024/1024;
                    // 文件系统可用总大小
                    surplusSize+= usage.getAvail()/1024/1024;
                    break;
            }
        }
        map.put("sumSize",new DiskInfo(sum,"已使用总大小"));
        map.put("surplusSize",new DiskInfo(surplusSize,"可用总大小"));
        return map;
    }

    /**获取网络信息
     * */
    public static Map<String,Object> net() throws Exception {
        Sigar sigar = new Sigar();
        int index=0;
        Map<String,Object> map=new HashMap<>();
        List<Long> listInfo;
        String ifNames[] = sigar.getNetInterfaceList();
        for (int i = 0; i < ifNames.length; i++) {
            String name = ifNames[i];
            NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
            //System.out.println("IP地址:  " + ifconfig.getAddress());// IP地址
            if ((ifconfig.getFlags() & 1L) <= 0L) {
                continue;
            }
            NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
            if(ifstat.getRxPackets()!=0){
                listInfo=new ArrayList<>();
                listInfo.add(ifstat.getRxBytes());
                listInfo.add(ifstat.getTxBytes());
                listInfo.add(ifstat.getRxErrors());
                listInfo.add(ifstat.getTxErrors());
                listInfo.add(ifstat.getRxDropped());
                listInfo.add(ifstat.getTxDropped());
                map.put("data"+index,new MyNetInfo(name,listInfo));
                index++;
            }
        }
        return map;
    }

    public static void ethernet() throws SigarException {
        Sigar sigar = null;
        sigar = new Sigar();
        String[] ifaces = sigar.getNetInterfaceList();
        for (int i = 0; i < ifaces.length; i++) {
            NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                continue;
            }
        }
    }

    public static String getNowTime(){
        /**获取时间
         * */
        Date nowtime = new Date( );
        SimpleDateFormat timeformat =
                new SimpleDateFormat ("yyyy/MM/dd hh:mm:ss");
        return timeformat.format(nowtime);
    }
}
