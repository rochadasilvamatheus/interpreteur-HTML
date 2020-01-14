import java.util.ArrayList;

class Corps extends ArrayList<Element> {

    public String docHtml() {
        String res = "<html>";
        return res + this.html() + "</html>";
    }

    public String html() {
        String res = "";
        for (Element e : this) {
            res = res + e.html();
        }
        return res;
    }
}

abstract class Element {

    abstract String html();
}

class Mot extends Element {
    String value;

    public Mot(String s) {
        value = s;
    }

    public String html() {
        return this.value;
    }
}

class LineBreak extends Element {

    public String html() {
        return "<br>";
    }
}

class Bf extends Element {
    Corps c;

    public Bf(Corps cc) {
        c = cc;
    }

    public String html() {

        return "<b>" + this.c.html() + "</b>";
    }
}

class It extends Element {
    Corps c;

    public It(Corps cc) {
        c = cc;
    }

    public String html() {
        return "<i>" + this.c.html() + "</i>";
    }
}

class Enumeration extends Element {

    ArrayList<Item> items;

    public Enumeration(ArrayList<Item> tmp) {
        items = tmp;
    }

    public String html() {
        String res = "<ol>";
        for (Item i : items) {
            res = res + " " + i.html();
        }
        res = res + " " + "</ol>";
        return res;
    }
}

class Item {
    private Corps c;

    public Item(Corps cc) {
        c = cc;
    }

    public String html() {
        return "<li>" + this.c.html() + "</li>";
    }
}