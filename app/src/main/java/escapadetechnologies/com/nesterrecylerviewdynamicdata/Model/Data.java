package escapadetechnologies.com.nesterrecylerviewdynamicdata.Model;

import java.util.List;

public class Data {

    private String title;
    private List<Section> sections;


    public Data() {
    }

    public Data(String title, List<Section> sections) {
        this.title = title;
        this.sections = sections;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
