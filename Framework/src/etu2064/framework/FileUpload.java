package etu2064.framework;
public class FileUpload {
    String nameFile;
    
    public String getnameFile() {return nameFile;}        public void setnameFile(String nameFile) {this.nameFile = nameFile;}
   
    public FileUpload(){}
    public FileUpload(String nameFile){
        this.setnameFile(nameFile);
    }
}