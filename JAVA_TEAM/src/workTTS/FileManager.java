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

	public static void checkFile(int index) throws IOException, UnsupportedAudioFileException {         // mp3������ �̹� �����Ǿ��ִ��� Ȯ���ϴ� �޼ҵ�
		File file = new File(Total_Frame.directoryPath.toString()+"\\TTS\\"+index+".mp3");
		
		if(!file.exists()) {
			SetFileTTS.create(Panel_1.storedTTS[index], index);
			
		}
	}
}
