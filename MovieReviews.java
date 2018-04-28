import java.util.*;
import java.io.*;
class Word {
  String s = null;
  int frequency = 0;
  Word(String s, int freq){
    this.s = s;
    this.frequency = freq;
  }
  public static Word contains(LinkedList<Word> list, String s1){
    Iterator<Word> it = list.listIterator();
    while(it.hasNext()){
      Word w1 = it.next();
      if(w1.s.equals(s1)){
        return w1;
      }
    }
    return null;
  }
  public int compareTo(Word w){
    return this.s.compareTo(w.s);
  }
  public boolean equals(Object obj){
    Word w = (Word)obj;
    return w.s.equals(this.s);
  }
  public void print(LinkedList<Word> list){
    Iterator<Word> it = list.listIterator();
    while(it.hasNext()){
      Word w1 = it.next();
      System.out.print(w1.frequency+" "+w1.s+" ");
      System.out.println();
    }
    System.out.println();
  }
}
class OWord {
  String s = null;
  double ratio = 0;
  OWord(String s, int n1, int n2){
    this.s = s;
    ratio = (n1*1.0)/n2;
  }
  public String toString(){
    return s+" "+ratio;
  }
}
class ReadFile {
  LinkedList<Word> list = new LinkedList<Word>();
  public LinkedList<Word> parse(File folder) throws IOException {
    for(File aFile : folder.listFiles()){
      String name = aFile.getName();
      BufferedReader br = null;
      try {
        br = new BufferedReader(new FileReader(folder+"/"+name));
      } catch(Exception ex){
        ex.printStackTrace();
      }
      String line = br.readLine();
      while(line != null){
        System.out.println(" line "+line);
        String sArray[] = line.split(" ");
        int n = 0;
        while(n < sArray.length){
          char cArray[] = sArray[n].toCharArray();
          if((cArray.length > 0) && (cArray[cArray.length-1] == '.')
          || cArray[cArray.length-1] == ',' ||
          cArray[cArray.length-1] == '?' ||
          cArray[cArray.length-1] == '!'){
            sArray[n] = sArray[n].substring(0, cArray.length-1);
          }
          Word w = Word.contains((LinkedList<Word>)list, sArray[n]);
          if(w != null){
            w.frequency++;
          } else {
            list.add(new Word(sArray[n], 1));
          }
          n++;
        }
        line = br.readLine();
      }
    }
    System.out.println(list.size()+" ");
    return (LinkedList<Word>)list;
  }
}
public class MovieReviews  {
  public static void main(String args[]) throws Exception {
    File folder1 = new File("neg");
    File folder2 = new File("pos");
    ReadFile r1 = new ReadFile();
    LinkedList<Word> list1 = r1.parse(folder1);
    LinkedList<Word> list2 = r1.parse(folder2);
    Collections.sort(list1, new Comparator<Word>(){
      public int compare(Word w1, Word w2){
        return w1.s.compareTo(w2.s);
      }
    });
    Collections.sort(list2, new Comparator<Word>(){
      public int compare(Word w1, Word w2){
        return w1.s.compareTo(w2.s);
      }
    });
    LinkedList<OWord> common = new LinkedList<OWord>();
    Iterator<Word> it1 = list1.listIterator();
    Iterator<Word> it2 = list2.listIterator();
    Word w1 = it1.next();
    Word w2 = it2.next();
    while(it1.hasNext() && it2.hasNext()){
      if(w1.equals(w2)){
        common.add(new OWord(w1.s, w1.frequency, w2.frequency));
        w1 = it1.next();
        w2 = it2.next();
      } else if(w1.compareTo(w2) > 0){
         w2 = it2.next();
      } else {
        w1 = it1.next();
      }
    }
    Collections.sort(common, new Comparator<OWord>(){
      public int compare(OWord w1, OWord w2){
        return ((Double)w1.ratio).compareTo((Double)w2.ratio);
      }
    });
    FileWriter fstream = new FileWriter("out.txt");
    BufferedWriter bOut = new BufferedWriter(fstream);
    for(OWord entry : common){
      System.out.println(entry.s+" "+entry.ratio+" ");
      bOut.write(entry.toString()+"\n");
    }
    bOut.close();
  }
}
