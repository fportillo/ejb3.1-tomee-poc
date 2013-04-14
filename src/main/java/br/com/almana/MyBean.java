package br.com.almana;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class MyBean
{
    public int add(int a, int b)
    {
        return a + b;
    }
}
