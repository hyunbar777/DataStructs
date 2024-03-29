package com.atguigu.HuffmanCode;

import java.io.*;
import java.util.*;

/**
 * @author z
 * @createdate 2019-08-27 19:41
 */

public class HuffmanCode {
    //1. 将赫夫曼编码表存放在 Map<Byte,String> 形式
    //   生成的赫夫曼编码表
    // 32=01, 97=100, 100=11000, 117=11001,
    // 101=1110, 118=11011, 105=101, 121=11010,
    // 106=0010, 107=1111, 108=000, 111=0011}
    static HashMap<Byte, String> huffmanCodes = new HashMap<>();
    //2. 在生成赫夫曼编码表示，需要去拼接路径, 定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(Arrays.toString(contentBytes));


        //3.0测试压缩
        String src = "Uninstall.xml";
        String dst = "Uninstall.zip";
        //zipFile(src, dst);
        System.out.println("压缩文件完成");

        //4.0测试解压文件
        String zipFile = "Uninstall.zip";
        String dstFile = "Uninstall2.xml";
        unZipFile(zipFile, dstFile);

        /*2.0
        //缩减的压缩，解压过程
        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是:" + Arrays.toString(huffmanCodesBytes) + " 长度= " + huffmanCodesBytes.length);

        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
        System.out.println("原来的字符串=" + new String(sourceBytes));
        */
        /*
        //1.0分析过程

        //1.1获取根据单个字符ASII一样的统计个数的list集合
        ArrayList<Node> nodes = getNode(contentBytes);
        //System.out.println(nodes);
        //1.2创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //1.3前序遍历赫夫曼树
        huffmanTreeRoot.preOrder();
        //1.4生成赫夫曼编码
        HashMap<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //System.out.println(huffmanCodes);
        //1.5测试
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println(Arrays.toString(huffmanCodeBytes));
        */
    }

    /**
     * @param src 你传入的希望压缩的文件的全路径
     * @param dst 我们压缩后将压缩文件放到哪个目录
     */
    private static void zipFile(String src, String dst) {
        FileInputStream is = null;
        FileOutputStream fos = null;
        ObjectOutputStream osw = null;
        try {

            is = new FileInputStream(src);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流, 存放压缩文件
            fos = new FileOutputStream(dst);
            //创建一个和文件输出流关联的ObjectOutputStream
            osw = new ObjectOutputStream(fos);
            //把 赫夫曼编码后的字节数组写入压缩文件
            osw.writeObject(huffmanBytes);
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码 写入压缩文件
            osw.writeObject(huffmanCodes);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (osw != null)
                    osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    private static void unZipFile(String zipFile, String dstFile) {

        FileInputStream fis = null;
        ObjectInputStream ois = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(zipFile);
            ois = new ObjectInputStream(fis);
            //读取byte数组  huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            HashMap<Byte, String> huffmanCodes = (HashMap<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            fos = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            fos.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //完成数据的解压
    //思路
    //1. 将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //   重写先转成 赫夫曼编码对应的二进制的字符串 "1010100010111..."
    //2.  赫夫曼编码对应的二进制的字符串 "1010100010111..." =》 对照 赫夫曼编码  =》 "i like like like java do you like a java"

    //编写一个方法，完成对压缩数据的解码

    /**
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {

        //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引,扫描 stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;

            while (flag) {
                //1010100010111...
                //递增的取出 key 1
                String key = stringBuilder.substring(i, i + count);//i 不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                } else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i 直接移动到 count
        }
        //当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
        //把list 中的数据放入到byte[] 并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;

    }

    /**
     * 将一个byte 转成一个二进制的字符串, 如果看不懂，可以参考我讲的Java基础 二进制的原码，反码，补码
     *
     * @param b    传入的 byte
     * @param flag 标志是否需要补高位如果是true ，表示需要补高位，如果是false表示不补, 如果是最后一个字节，无需补高位
     * @return 是该b 对应的二进制的字符串，（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存 b
        int temp = b;//将 b 转成 int
        //如果是正数我们还存在补高位
        if (flag) {
            temp |= 256;//按位与 256  1 0000 0000  | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }
    //使用一个方法，将前面的方法封装起来，便于我们的调用.

    /**
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过 赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        ArrayList<Node> nodes = getNode(bytes);
        //根据 nodes 创建的赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //对应的赫夫曼编码(根据 赫夫曼树)
        HashMap<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    /**
     * 编写一个方法，将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
     *
     * @param bytes        这时原始的字符串对应的 byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的 byte[]
     * 举例： String content = "i like like like java do you like a java"; =》 byte[] contentBytes = content.getBytes();
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes, HashMap<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //System.out.println(stringBuilder);
        //统计返回  byte[] huffmanCodeBytes 长度
        //一句话 int len = (stringBuilder.length() + 7) / 8;
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String b;
            if (i + 8 > stringBuilder.length()) {
                b = stringBuilder.substring(i);
            } else {
                b = stringBuilder.substring(i, i + 8);
            }
            //将strByte 转成一个byte,放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(b, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    private static HashMap<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", sb);
        //处理root的右子树
        getCodes(root.right, "1", sb);
        return huffmanCodes;
    }

    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param node 传入结点
     * @param code 路径： 左子结点是 0, 右子结点 1
     * @param sb   用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder sb) {
        StringBuilder sbb = new StringBuilder(sb);
        sbb.append(code);
        if (node != null) {
            //判断当前node 是叶子结点还是非叶子结点
            if (node.data == null) {//非叶子结点,不存数据data
                //向左递归
                getCodes(node.left, "0", sbb);
                //向右递归
                getCodes(node.right, "1", sbb);
            } else {//说明是一个叶子结点
                //就表示找到某个叶子结点的最后
                huffmanCodes.put(node.data, sbb.toString());
            }
        }
    }

    //可以通过List 创建对应的赫夫曼树
    private static Node createHuffmanTree(ArrayList<Node> nodes) {

        while (nodes.size() > 1) {
            //每次添加完节点，并且删除原来的节点后，是乱序的，只能每次循环都要排序
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Node left = nodes.get(0);
            //取出第二颗最小的二叉树
            Node right = nodes.get(1);
            //创建一颗新的二叉树,它的根节点 没有data, 只有权值
            Node parent = new Node(null, left.weight + right.weight);

            parent.left = left;
            parent.right = right;
            //将已经处理的两颗二叉树从nodes删除
            nodes.remove(left);
            nodes.remove(right);
            //将新的二叉树，加入到nodes
            nodes.add(parent);
        }
        //nodes 最后的结点，就是赫夫曼树的根结点
        return nodes.get(0);
    }

    /**
     * @param bytes 接收字节数组
     * @return 返回的就是 List 形式   [Node[date=97 ,weight = 5], Node[]date=32,weight = 9]......],
     */
    private static ArrayList<Node> getNode(byte[] bytes) {
        HashMap<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer i = map.get(b);
            if (i == null) {
                map.put(b, 1);
            } else
                map.put(b, ++i);
        }
        ArrayList<Node> list = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue()));
        }

        return list;
    }
}

class Node implements Comparable<Node> {
    // 存放数据(字符)本身，比如'a' => 97 ' ' => 32
    Byte data;
    //权值, 表示字符出现的次数
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
