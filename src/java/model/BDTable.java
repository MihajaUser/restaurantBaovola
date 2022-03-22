/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author Tiavina P(anda)C
 */
public class BDTable {
    private Connection Connect;
    public BDTable() throws Exception
    {
        this.Connect=new Connexion().getConnect();
    }
    public void commit() throws Exception
    {
        String requete="commit";
        Statement state=Connect.createStatement();
        state.executeUpdate(requete);
        return;
    }
    public void rollback() throws Exception
    {
        String requete="rollback";
        Statement state=Connect.createStatement();
        state.executeUpdate(requete);
        return;
    }
    public void insert(Object o,String nTable,String nPrimaryKey) throws Exception
    {
        Class classe=o.getClass();
        Field[] attribut=classe.getDeclaredFields();
        Method[] get=classe.getMethods();
        String requete1 = "INSERT INTO "+nTable+"()";
        String requete2 = "VALUES()";
        if(nPrimaryKey==null)
        {
            nPrimaryKey="id"+nTable;
        }
        for(int i=0;i<attribut.length;i++)
        {
            if(attribut[i].getName().equalsIgnoreCase(nPrimaryKey)==true)
            {
                ResultSet result=this.getResultat("select max("+nPrimaryKey+") from "+nTable);
                int numero=1;
                while(result.next()) {
                    numero=result.getInt("max");
                    numero++;
                }
                for(int i2=0;i2<get.length;i2++)
                {
                    if(get[i2].getName().equalsIgnoreCase("set"+nPrimaryKey)==true)
                    {
                        get[i2].invoke(o,numero);
                    }
                }
                String id=""+numero;
                String complete1=","+nPrimaryKey+")";
                String complete2=",'"+id+"')";
                if(i==0)
                {
                    complete1=nPrimaryKey+")";
                    complete2="'"+id+"')";
                }
                String r1=requete1.replace(")",complete1);
                String r2=requete2.replace(")",complete2);
                requete1=r1;
                requete2=r2;
                continue;
            }
            for(int i2=0;i2<get.length;i2++)
            {
                if(get[i2].getName().equalsIgnoreCase("get"+attribut[i].getName())==true)
                {
                    Object valget=get[i2].invoke(o);
                    String val=valget+"";
                    String complete1=","+attribut[i].getName()+")";
                    String complete2=",'"+val+"')";
                    if(i==0)
                    {
                            complete1=attribut[i].getName()+")";
                            complete2="'"+val+"')";
                    }
                    String r1=requete1.replace(")",complete1);
                    String r2=requete2.replace(")",complete2);
                    requete1=r1;
                    requete2=r2;
                }
            } 

        }
        String requete=requete1+" "+requete2;
        Statement state=Connect.createStatement();			
        state.executeUpdate(requete);
        return;
    }
    public void update(Object o,String nTable,String nPrimaryKey) throws Exception
    {
        Class classe=o.getClass();
        Field[] attribut=classe.getDeclaredFields();
        Method[] get=classe.getMethods();
        String requete1 = "Update "+nTable+" set ";
        String requete2 = "where "+nPrimaryKey+"='";
        if(nPrimaryKey==null)
        {
            requete2="where id"+nTable+"='";
        }
        for(int i=0;i<attribut.length;i++)
        {
            for(int i2=0;i2<get.length;i2++)
            {
                    if(get[i2].getName().equalsIgnoreCase("get"+attribut[i].getName())==true)
                {
                    Object valget=get[i2].invoke(o);
                    String val=valget+"";
                    int ok=0;
                    String verif="id"+nTable;
                    if(nPrimaryKey==null)
                    {
                        if(attribut[i].getName().equalsIgnoreCase(verif)==true)
                        {
                            ok=1;
                        }
                    }
                    if(attribut[i].getName().equalsIgnoreCase(nPrimaryKey)==true || ok==1)
                    {
                        requete2=requete2+""+val+"'";
                        break;
                    }
                    String complete=attribut[i].getName()+"='"+val+"',";
                    if(i==attribut.length-1)
                    {
                        complete=attribut[i].getName()+"='"+val+"'";
                    }
                    requete1=requete1+""+complete;
                }
            }
        }
        String requete=requete1+" "+requete2;
        System.out.print(requete);
        Statement state=Connect.createStatement();			
        state.executeUpdate(requete);
        commit();
        return;
    }
    public Object[] select(Object filtre,String nTable)throws Exception
    {
        Class classe=filtre.getClass();
        Field[] attribut=classe.getDeclaredFields();
        Method[] get=classe.getMethods();
        Vector indice=new Vector();
        Vector valFiltre=new Vector();
        Vector resultat=new Vector();
        int nbResultat=0;
        for(int i=0;i<attribut.length;i++)
        {
            for(int i2=0;i2<get.length;i2++)
            {
                if(get[i2].getName().equalsIgnoreCase("get"+attribut[i].getName())==true)
                {
                    Object valget=get[i2].invoke(filtre);
                    if(attribut[i].getType().getSimpleName().equalsIgnoreCase("int")==true)
                    {
                        int j=(int)valget;
                        if(j==0)
                        {
                            break;
                        }
                    }
                    if(attribut[i].getType().getSimpleName().equalsIgnoreCase("double")==true)
                    {
                        double j=(double)valget;
                        if(j==0)
                        {
                            break;
                        }
                    }
                    if(valget==null || valget.equals(0))
                    {
                        break;
                    }
                    indice.add(i);
                    valFiltre.add(valget);
                }
            }
        }

        String requete1="select * from "+nTable;
        String requete2="where";
        for(int i=0;i<indice.size();i++)
        {
            int ind=(int)indice.elementAt(i);
            String ajout=attribut[ind].getName()+"='"+valFiltre.elementAt(i)+"'";
            if(i!=indice.size()-1)
            {
                ajout=ajout+" and";
            }
            requete2=requete2+" "+ajout;
        }
        if(indice.isEmpty()==true)
        {
            requete2="";
        }
        String requete=requete1+" "+requete2;

        ResultSet result=getResultat(requete);
        while(result.next())
        {
            Object o=classe.newInstance();
            for(int i=0;i<attribut.length;i++)
            {
                if(attribut[i].getName().equalsIgnoreCase("montant_gain")==true || attribut[i].getName().equalsIgnoreCase("montant_val")==true)
                {
                    continue;
                }
                for(int i2=0;i2<get.length;i2++)
                {
                    if(get[i2].getName().equalsIgnoreCase("set"+attribut[i].getName())==true)
                    {
                        if(attribut[i].getType().getSimpleName().equalsIgnoreCase("int")==true)
                        {
                            Integer entry=result.getInt(attribut[i].getName());
                            get[i2].invoke(o,entry);
                        }
                        else if(attribut[i].getType().getSimpleName().equalsIgnoreCase("double")==true)
                        {
                            double entry=result.getFloat(attribut[i].getName());
                            get[i2].invoke(o,entry);
                        }
                        else if(attribut[i].getType().getSimpleName().equalsIgnoreCase("float")==true)
                        {
                            float entry=result.getFloat(attribut[i].getName());
                            get[i2].invoke(o,entry);
                        }
                        else
                        {
                            String entry=result.getString(attribut[i].getName());
                            get[i2].invoke(o,entry);
                        }
                        break;
                    }
                }
            }
            resultat.add(o);
            nbResultat++;
        }

        Object[] valiny=new Object[nbResultat];
        for(int i=0;i<nbResultat;i++)
        {
                valiny[i]=resultat.elementAt(i);
        }
        return valiny;
    }
    public Connection getConnect()
    {
        return this.Connect;
    }
    public void setConnect(Connection newConnect) throws Exception
    {
        this.Connect=newConnect;
    }
    public ResultSet getResultat(String requete) throws Exception
    {
        Statement state=Connect.createStatement();			
        ResultSet resultat=state.executeQuery(requete);
        return resultat;
    } 
}
