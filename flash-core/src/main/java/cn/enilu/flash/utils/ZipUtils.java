package cn.enilu.flash.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


/**
 * 压缩和解压缩
 * 
 * @author
 * */
public class ZipUtils {
	/**
	 * 日志记录，为静态变量，防止多次实例化
	 * 
	 * */
//	private static Logger log = Logger.getLogger(ZipUtils.class);
	/**
	 * 每次读入大小
	 */
	private static final int BUFFER = 4096;

	/**
	 * 做成压缩文件
	 * 
	 * @param baseDir
	 *            压缩文件夹
	 * @param zipFileName
	 *            生成压缩文件名
	 * @return 是否成功
	 */
	public static boolean zipFile(String baseDir, String zipFileName) {
		try {
			// 获取文件夹下所有文件
			List<File> fileList = getSubFiles(new File(baseDir));
			ZipOutputStream zos;
			zos = new ZipOutputStream(new FileOutputStream(zipFileName));
			for (int i = 0; i < fileList.size(); i++) {
				File f = fileList.get(i);
				zipAddFile(zos, f, getAbsFileName(baseDir, f));
			}
			zos.close();
			return true;
		} catch (Exception e) {
//			log.error("生成压缩文件错误:错误原因如下:" + e.getMessage());
		}
		return false;
	}
	
	/**
	 * 做成压缩文件
	 * 
	 * @param baseDir
	 *            压缩文件夹
	 * @return 是否成功
	 */
	public static String zipFile(String baseDir) {
		ZipOutputStream zos = null;
		try {
			File base = new File(baseDir);
			String target = base.getPath() + ".zip";
			// 获取文件夹下所有文件
			List<File> fileList = getSubFiles(base);
			zos = new ZipOutputStream(new FileOutputStream(target));
			for (int i = 0; i < fileList.size(); i++) {
				File f = fileList.get(i);
				zipAddFile(zos, f, getAbsFileName(baseDir, f));
			}
			return target;
		} catch (Exception e) {
//			log.error("生成压缩文件错误:错误原因如下:" + e.getMessage());
		} finally {
			try {
				if (zos != null) {
					zos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 压缩包中添加文件
	 * 
	 * @param zos
	 *            压缩包对象
	 * @param file
	 *            文件对象
	 * @param filePath
	 *            在压缩包中的路径
	 * @return 是否成功
	 */
	private static boolean zipAddFile(ZipOutputStream zos, File file,
									  String filePath) throws FileNotFoundException, IOException {
		ZipEntry ze = new ZipEntry(filePath);
		ze.setSize(file.length());
		ze.setTime(file.lastModified());
		zos.putNextEntry(ze);

		InputStream is = new BufferedInputStream(new FileInputStream(file));
		int readLen = 0;
		byte[] buf = new byte[BUFFER];
		while ((readLen = is.read(buf, 0, BUFFER)) != -1) {
			zos.write(buf, 0, readLen);
		}
		// zos.setEncoding("GBK");
		is.close();
		return true;
	}

	/**
	 * 解压缩文件
	 * 
	 * @param zipName
	 *            包的文件名
	 * @param targetDirName
	 *            需解压到的目录
	 * @return 是否成功
	 */
	public static boolean upZipFile(String zipName, String targetDirName) {
		ZipFile zipFile =null;
		try {
			zipFile = new ZipFile(zipName);
			if (!targetDirName.endsWith(File.separator)) {
				targetDirName += File.separator;
			}
			ZipEntry zn = null;
			String entryName = null;
			String targetFileName = null;
			byte[] buffer = new byte[BUFFER];
			int readLen = 0;
			// 获取ZIP文件里所有的文件条目的名字
			Enumeration<?> entrys = zipFile.entries();
			while (entrys.hasMoreElements()) {
				zn = (ZipEntry) entrys.nextElement();
				entryName = zn.getName();
				targetFileName = targetDirName + entryName;
				if (zn.isDirectory()) {
					// 如果zn是一个目录，则创建目录
					new File(targetFileName).mkdirs();
					continue;
				} else {
					// 如果zn是一个文件，则创建父目录
					new File(targetFileName).getParentFile().mkdirs();
				}
				// 创建文件
				File targetFile = new File(targetFileName);
				FileOutputStream os = new FileOutputStream(targetFile);
				// 从ZipFile对象中打开entry的输入流
				InputStream is = zipFile.getInputStream(zn);
				while ((readLen = is.read(buffer)) != -1) {
					os.write(buffer, 0, readLen);
				}
				// 关闭流
				os.close();
				is.close();
			}
			return true;
		} catch (Exception e) {
//			log.error("解压缩错误:错误原因如下:" + e.getMessage());
		}finally{
			try{
			zipFile.close();
			}catch(Exception e){}
		}
		return false;
	}

	/**
	 * 生成文件在压缩包中的路径
	 * 
	 * @param baseDir
	 *            压缩包所在文件夹路径
	 * @param realFile
	 *            文件对象
	 * @return 文件在压缩包中的路径
	 */
	private static String getAbsFileName(String baseDir, File realFile) {
		File real = realFile;
		File base = new File(baseDir);
		String ret = real.getName();
		while (true) {
			real = real.getParentFile();
			if (real == null) {
				break;
			}
			if (real.equals(base)) {
				break;
			} else {
				ret = real.getName() + "/" + ret;
			}
		}
		return ret;
	}

	/**
	 * 获取文件夹下所有文件
	 * 
	 * @param baseDir
	 *            文件夹路径
	 * @return 文件集合
	 */
	private static List<File> getSubFiles(File baseDir) {
		List<File> ret = new ArrayList<File>();
		File[] tmp = baseDir.listFiles();
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i].isFile()) {
				ret.add(tmp[i]);
			}
			if (tmp[i].isDirectory()) {
				ret.addAll(getSubFiles(tmp[i]));
			}
		}
		return ret;
	}
	
	public static boolean isExist(String path) {
		File file = new File(path);
		return file.exists();
	}
	
	public static boolean createDirectory(String path) {
		File file = new File(path);
		if (!isExist(path)) {
			file.mkdirs();
		}
		return true;
	}
	
	public static boolean deleteDirectory(String path) {
		File f = new File(path);
		if (isExist(path)) {
			if (f.isDirectory()) {
				File[] files = f.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteDirectory(files[i].getPath());
				}
				f.delete();
			} else if (f.isFile()) {
				f.delete();
			}
		}
		return true;
	}

}
