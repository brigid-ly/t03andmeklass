import java.io.*;
import java.net.*;
public class Voistlusprotokoll{
	String failiAsukoht;
	public Voistlusprotokoll(String failiAsukoht){
		this.failiAsukoht=failiAsukoht;
	}
	BufferedReader kysiLugeja(){
	  try{
		if(failiAsukoht.startsWith("http://")){
			return new BufferedReader(new InputStreamReader(
			   new URL(failiAsukoht).openConnection().getInputStream()));
		}
		else {
			return new BufferedReader(new FileReader(failiAsukoht));
		}
	  } catch(Exception ex){
		  return null;
	  }
	}
	public int suurimTulemus(int tulbanr) throws IOException{
		BufferedReader lugeja=kysiLugeja();
		boolean alustatud=false;
		String rida=lugeja.readLine();
		int puuduvaid=0;
		int maxTulemus=0;
		while(rida!=null){
			String[] m=rida.split(" ");
			 try{
			  int tulemus=Integer.parseInt(m[tulbanr]);
			  if(alustatud){
				  if(tulemus>maxTulemus){maxTulemus=tulemus;}
			  } else {
				  maxTulemus=tulemus;
				  alustatud=true;
			  }
			 } catch(Exception veaandmed){
				 puuduvaid++;
			 }
			 rida=lugeja.readLine();
		}
		if(puuduvaid>0){System.err.println("Puudu "+puuduvaid);}
		lugeja.close();
		return maxTulemus;
	}

}
