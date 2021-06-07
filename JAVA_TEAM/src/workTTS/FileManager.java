package workTTS;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class FileManager {

	public static String getFileExtension(Path p) {           // 파일 확장자 가져오기
		File file = new File(p.toString());                   // 파일 생성
		String filename = file.getName();                     // 파일의 이름을 가져오기
		String fileExtension = "";                            // 확장자를 저장할 객체
		
		int i = filename.lastIndexOf('.');                    // . 부분의 인덱스 저장하는 변수
		if(i>0) fileExtension = filename.substring(i+1);      // . 다음부분들을 저장해줌
		
		return fileExtension;                                 // 확장자명 리턴
	}
	
	public static String getFileName(String p) {                // 파일 이름 가져오기 (확장자를 제외한)
		String filename = p;
		
		int i = filename.lastIndexOf('.');                    // . 부분의 인덱스 저장하는 변수
		if(i>0) filename = filename.substring(0, i);      // . 다음부분들을 저장해줌
		
		return filename;                                 // 확장자명 리턴
	}
	
	public static void createFolder(File folder) {            // 폴더 생성 메소드
			
			folder.mkdir();
			JOptionPane.showMessageDialog(null, "TTS폴더가 생성되었습니다.");
	}
	
	public static void deleteFiles(String path) {   // 파일 제거 메소드
	      // *주의 - 경로에 있는 모든 파일을 제거합니다. 중요한 폴더가 있는지 확인하세요.
	          File folder = new File(path);
	          try {
	                   File[] folder_list = folder.listFiles(); //파일리스트 얻어오기
	               
	                   for (int i = 0; i < folder_list.length; i++) {
	                      if (folder_list[i].isFile())
	                         folder_list[i].delete();
	                      System.out.println(folder_list[i]);
	                   }
	             
	          } catch (Exception e) {
	             e.getStackTrace();
	          }
	   }
	   
	   public static void deleteAllFile(Path path) throws IOException, UnsupportedAudioFileException {   // text 및 mp3 파일 제거 메소드
	      deleteFiles(path.toString()+"\\TTS");      // mp3 파일 제거
	      deleteFiles(path.toString());   // text 파일 제거
	      Panel_1.UpdateTextList(Total_Frame.panel1, Total_Frame.panel3);
	   }

	public static void checkFile(int index) 
			throws IOException, UnsupportedAudioFileException {         // mp3파일이 이미 생성되어있는지 확인하는 메소드
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
