import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class VersionPacking {
//	static String SVNFILENAME = "c:\\svn.xml";
	static String mainPath = "";
//	static Integer mainVersion = 0;
	
	public static void main(String[] args) throws Exception {
    
		//获取传进来的路径
		if(null != args && args.length > 1) {
			String path =  args[0];		//SVNFILENAME;// args[0];
			String outPath = args[1];		//"c:\\out.dat";
			System.out.println("#资源路径" + path);
			System.out.println("#输出路径" + outPath);
			
			//获取XML
			Document documet = GetDocument(path);
			//解析
			HashMap<String, Integer> map = readFromDocument(documet);
			//输出二进制文件
			outputBinary(map, outPath);
			//压缩
			compress(outPath);	
		}
		else {
			System.out.println("没输入路径,没有导出任何的版本号.");
		}
	}
 
	private static void outputBinary(HashMap<String, Integer> map, String outPath) throws IOException {
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outPath)));
		out.writeShort(map.size());
		Iterator  li = map.entrySet().iterator();
		while(li.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>)li.next();
			out.writeUTF(entry.getKey());
			out.writeShort(entry.getValue());
		}
		out.close();
	}

	private static HashMap<String, Integer> readFromDocument(Document documet) {
//		Dictionary<String, Integer> dic = new  Dictionary<String, Integer>();
		//获取主路径
		Element element = (Element)documet.getElementsByTagName("target").item(0);
		mainPath = element.getAttribute("path") + "\\";
		mainPath = mainPath.replaceAll("\\\\", "\\\\\\\\");;
		
		HashMap<String, Integer> map = new HashMap<String, Integer>(); 
		NodeList list = documet.getElementsByTagName("entry");
		for(int i = 0; i < list.getLength(); i++) {
			element = (Element)list.item(i);
			String path = element.getAttribute("path");
			File file = new File(path);
			if(file.isFile()) {
				if(path.length() != mainPath.length()) path = path.replaceFirst(mainPath, "");
				//wc-status   commit revision
				Element verEle = (Element)(element.getElementsByTagName("commit").item(0));
				Integer version = Integer.parseInt( verEle.getAttribute("revision") );
				System.out.println(path + ":" + version);
				map.put(path, version);
			}
		}
		return map;
	}

	private static Document GetDocument(String path) throws Exception {
		System.out.println("#开始从路径里面读取SVN信息");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbBuilder = factory.newDocumentBuilder();
		return dbBuilder.parse(path);
	}
	 
    private static void compress(String path) throws Exception {
    	System.out.println("#start to compress " + path);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

        byte[] temp = new byte[1024];
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        in.close();
        byte[] data = out.toByteArray();
        byte[] output = CompressionUtil.compress(data, CompressionUtil.Level.BEST_COMPRESSION);
        System.out.println("before : " + (data.length ) + "byte");		/// 1024
        System.out.println("after : " + (output.length ) + "byte");		// 1024

        FileOutputStream fos = new FileOutputStream(path);
        fos.write(output);
        out.close();
        fos.close();
    }
 
//    public void testDecompress() throws Exception {
//
//        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
//                "/Users/lichengwu/tmp/out_put.txt.bak.compress"));
//        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
//        byte[] temp = new byte[1024];
//        int size = 0;
//        while ((size = in.read(temp)) != -1) {
//            out.write(temp, 0, size);
//        }
//        in.close();
//        byte[] data = out.toByteArray();
//        byte[] output = CompressionUtil.decompress(data);
//        System.out.println("before : " + (data.length / 1024) + "k");
//        System.out.println("after : " + (output.length / 1024) + "k");
//
//        FileOutputStream fos = new FileOutputStream("/Users/lichengwu/tmp/out_put.txt.bak.decompress");
//        fos.write(output);
//        out.close();
//        fos.close();
//    }
}
