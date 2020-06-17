

public class Column {

    private String title;
    private int width;

    public void setTitle(String title) {

        this.title = title;
    }

    public void setWidth(Integer width) {

        this.width = width;
    }

    public String getTitle() {

        return title;
    }

    public int getWidth() {
        return width;
    }
    @Override
    public String toString(){
        return  ""+title+"";
    }
}