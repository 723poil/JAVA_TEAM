package workTTS;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;

public class SetFileTTS {

	public static void create(String textContent, int index) throws IOException {
		
		TextToSpeechClient inputFileContent = TextToSpeechClient.create();
		
		SynthesisInput textInput = SynthesisInput.newBuilder().setText(textContent).build();
		
		VoiceSelectionParams textSpeech = VoiceSelectionParams.newBuilder().setLanguageCode("ko-KR").setSsmlGender(SsmlVoiceGender.NEUTRAL).build();
		
		AudioConfig audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();
		
		SynthesizeSpeechResponse response = inputFileContent.synthesizeSpeech(textInput, textSpeech, audioConfig);
		
		ByteString audioContents = response.getAudioContent();
		
		try(OutputStream out = new FileOutputStream(Total_Frame.directoryPath.toString()+"\\TTS\\"+index+".mp3")) {
			out.write(audioContents.toByteArray());
			
		}
		
	}
}
