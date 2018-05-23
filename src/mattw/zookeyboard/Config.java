package mattw.zookeyboard;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Config {

    private File file;
    private Data data = new Data();
    private Gson gson = new Gson();

    public Config(String file) {
        this.file = new File(file);
        if (this.file.exists()) {
            load();
        } else {
            save();
        }
    }

    public void save() {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(gson.toJson(this.data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
            String line;
            StringBuilder text = new StringBuilder();
            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            this.data = gson.fromJson(text.toString(), Data.class);
        } catch (Exception e) {
            e.printStackTrace();
            this.data = new Data();
        }
    }

    public class Data {
        public String[] audio_sources = {"sound/default", "sound/naughty", "sound/explosions"};
    }

    public String[] getAudioSources() {
        return data.audio_sources;
    }

}

