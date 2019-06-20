import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Search {
    private List<String> files = new ArrayList<>();
    private List<String> results = new ArrayList<>();

    private void searchFile(String dir, List<String> extensions)
    {
        File file = new File(dir);
        for(File el : file.listFiles())
        {
            if(el.isDirectory()) searchFile(el.getAbsolutePath(),extensions);
            else
                {
                    for(String n : extensions){
                        if(el.getName().endsWith(n))this.files.add(el.getAbsolutePath());
                    }
                }

        }
    }
    private boolean searchText(String text, String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path))).contains(text);
    }

    private void search(String dir, String text, List<String> extensions)
    {
        searchFile(dir,extensions);
        Thread one = new Thread(()-> {
            for (int i = 0; i < files.size()/2; i++) {
                try {
                    if (searchText(text, files.get(i))) results.add(files.get(i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
            Thread two = new Thread(()->{
                for(int i=files.size()-1;i>=files.size()/2;i--)
                {
                    try {
                        if (searchText(text, files.get(i))) results.add(files.get(i));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        });
            one.start();
            two.start();
        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void result(){
        for(String s : this.results)
            System.out.println(s);
    }

    public static void main(String[] args)
    {
        List<String>extensions = new ArrayList<>();
        extensions.add(".txt");
        String dir = "D:\\1";
        String text = args[0];
        Search poisk = new Search();
        poisk.search(dir,text,extensions);
        poisk.result();
    }
}
