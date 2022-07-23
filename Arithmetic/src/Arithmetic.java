public class Arithmetic
{
    private int var1;
    private int var2;

    public Arithmetic (int var1, int var2)
    {   this.var1 = var1;
        this.var2 = var2;
    }
    public Arithmetic (int var1)
    {   this(var1,0);
    }
    public Arithmetic ()
    {   this(0,0);
    }

    public void setVar1 (int var) { var1 = var; }
    public void setVar2 (int var) { var2 = var; }

    public int getVar1() { return var1; }
    public int getVar2() { return var2; }

    public int getSum ()
    {   return (var1 + var2);
    }
    public int getSub ()
    {   return (var1 - var2);
    }
    public int getMul ()
    {   return (var1 * var2);
    }
    public int getMod ()
    {   if (var2 != 0) return (var1 % var2);
        return 0;
    }
    public int getDiv ()
    {   if (var2 != 0) return (var1 / var2);
        return 0;
    }
    public int getMin ()
    {   if (var1 < var2 ) return var1; else return var2;
    }
    public int getMax ()
    {   if (var1 > var2 ) return var1; else return var2;
    }


}
