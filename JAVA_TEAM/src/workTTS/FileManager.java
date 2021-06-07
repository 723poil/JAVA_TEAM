package workTTS;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class FileManager {

	public static String getFileExtension(Path p) {           // ���� Ȯ���� ��������
		File file = new File(p.toString());                   // ���� ����
		String filename = file.getName();                     // ������ �̸��� ��������
		String fileExtension = "";                            // Ȯ���ڸ� ������ ��ü
		
		int i = filename.lastIndexOf('.');                    // . �κ��� �ε��� �����ϴ� ����
		if(i>0) fileExtension = filename.substring(i+1);      // . �����κе��� ��������
		
		return fileExtension;                                 // Ȯ���ڸ� ����
	}
	
	public static String getFileName(String p) {                // ���� �̸� �������� (Ȯ���ڸ� ������)
		String filename = p;
		
		int i = filename.lastIndexOf('.');                    // . �κ��� �ε��� �����ϴ� ����
		if(i>0) filename = filename.substring(0, i);      // . �����κе��� ��������
		
		return filename;                                 // Ȯ���ڸ� ����
	}
	
	public static void createFolder(File folder) {            // ���� ���� �޼ҵ�
			
			folder.mkdir();
			JOptionPane.showMessageDialog(null, "TTS������ �����Ǿ����ϴ�.");
	}
	
	public static void deleteFiles(String path) {   // ���� ���� �޼ҵ�
	      // *���� - ��ο� �ִ� ��� ������ �����մϴ�. �߿��� ������ �ִ��� Ȯ���ϼ���.
	          File folder = new File(path);
	          try {
	                   File[] folder_list = folder.listFiles(); //���ϸ���Ʈ ������
	               
	                   for (int i = 0; i < folder_list.length; i++) {
	                      if (folder_list[i].isFile())
	                         folder_list[i].delete();
	                      System.out.println(folder_list[i]);
	                   }
	             
	          } catch (Exception e) {
	             e.getStackTrace();
	          }
	   }
	   
	   public static void deleteAllFile(Path path) throws IOException, UnsupportedAudioFileException {   // text �� mp3 ���� ���� �޼ҵ�
	      deleteFiles(path.toString()+"\\TTS");      // mp3 ���� ����
	      deleteFiles(path.toString());   // text ���� ����
	      Panel_1.UpdateTextList(Total_Frame.panel1, Total_Frame.panel3);
	   }

	public static void checkFile(int index) 
			throws IOException, UnsupportedAudioFileException {         // mp3������ �̹� �����Ǿ��ִ��� Ȯ���ϴ� �޼ҵ�
		File file = new File(Total_Frame.directoryPath.toString()+"\\TTS\\"+index+".mp3");
		
		if(!file.exists()) {
			SetFileTTS.create(Panel_1.storedTTS[index], index);
		}
	}
	
	public static void deleteOneFile() throws IOException, UnsupportedAudioFileException {
		File textFile = new File(Total_Frame.directoryPath.toString()+"\\"+(Panel_1.count / 10)+"_"+(Panel_1.count % 10)+".txt");
		File mp3File = new File(Total_Frame.directoryPath.toString()+"\\TTS\\"+Panel_1.count+".mp3");
		
		textFile.delete();
		mp3File.delete();
		
		Panel_1.count--;
		Panel_1.storedTTS[Panel_1.count+1] = "";
		Panel_1.UpdateTextList(Total_Frame.panel1, Total_Frame.panel3);
	}
}
