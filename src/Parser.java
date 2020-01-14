import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

public class Parser {
    protected LookAhead1 reader;

    @Contract(pure = true)
    public Parser(LookAhead1 r) {
        reader = r;
    }

    public Corps documentNonTerm() throws Exception {
        Corps res = corpsNonTerm();
        reader.eat(Sym.EOF);
        return res;
    }

    public Corps corpsNonTerm() throws Exception {
        reader.eat(Sym.BEGINDOC);
        Corps res = suiteElementsNonTerm();
        reader.eat(Sym.ENDDOC);
        return res;
    }

    public Corps suiteElementsNonTerm() throws Exception {
        Corps res = new Corps();
        if (!reader.check(Sym.ENDDOC) && !reader.check(Sym.RACC) && !reader.check(Sym.ENDENUM) && !reader.check(Sym.ITEM)) {
            Element e = elementNonTerm();
            res.add(e);
            Corps tmp = suiteElementsNonTerm();
            res.addAll(tmp);
        }
        return res;
    }

    public Element elementNonTerm() throws Exception {
        if (reader.check(Sym.MOT)) {
            String v = reader.getStringValue();
            reader.eat(Sym.MOT);
            return new Mot(v);
        }
        if (reader.check(Sym.LINEBREAK)) {
            reader.eat(Sym.LINEBREAK);
            return new LineBreak();
        }
        if (reader.check(Sym.BF)) {
            reader.eat(Sym.BF);
            reader.eat(Sym.LACC);
            Corps res = suiteElementsNonTerm();
            reader.eat(Sym.RACC);
            return new Bf(res);
        }
        if (reader.check(Sym.IT)) {
            reader.eat(Sym.IT);
            reader.eat(Sym.LACC);
            Corps res = suiteElementsNonTerm();
            reader.eat(Sym.RACC);
            return new It(res);
        } else {
            ArrayList<Item> items = enumerationNonTerm();
            return new Enumeration(items);
        }
    }

    public ArrayList<Item> enumerationNonTerm() throws Exception {
        reader.eat(Sym.BEGINENUM);
        ArrayList<Item> items = new ArrayList<>();
        Enumeration res = new Enumeration(items);
        res.items = suiteItemsNonTerm();
        reader.eat(Sym.ENDENUM);
        return res.items;
    }

    public ArrayList<Item> suiteItemsNonTerm() throws Exception {
        ArrayList<Item> items = new ArrayList<>();
        Enumeration res = new Enumeration(items);
        if (!reader.check(Sym.ENDENUM)) {
            Item i = new Item(itemNonTerm());
            res.items.add(i);
            ArrayList<Item> tmp = suiteItemsNonTerm();
            res.items.addAll(tmp);
        }
        return res.items;
    }

    public Corps itemNonTerm() throws Exception {
        reader.eat(Sym.ITEM);
        Corps res = suiteElementsNonTerm();
        return res;
    }

}
