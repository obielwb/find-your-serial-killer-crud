package com.company.bd.dbos;

public class SerialKiller {
    private int id;
    public int getId () { return this.id; }
    public void setId (int id) throws Exception
    {
        if (id < 0)
            throw new Exception("Id não pode ser nulo");
        this.id = id;
    }

    private String nome;
    public String getNome () { return this.nome; }
    public void setNome (String nome) throws Exception
    {
        if (nome == null)
            throw new Exception("Nome não pode ser nulo");
        this.nome = nome;
    }

    private String armas;
    public String getArmas () { return this.armas; }
    public void setArmas (String armas) throws Exception
    {
        if (armas == null)
            throw new Exception("Armas não pode ser nulo");
        this.armas = armas;
    }

    private int vezesContrado;
    public int getVezesContrado () { return this.vezesContrado; }
    public void setVezesContrado (int vezesContrado) throws Exception
    {
        if (vezesContrado < 0)
            throw new Exception("O número de vezes contrado não pode ser negativo.");
        this.vezesContrado = vezesContrado;
    }

    private int mortesConfirmadas;
    public int getMortesConfirmadas () { return this.mortesConfirmadas; }
    public void setMortesConfirmadas (int mortesConfirmadas) throws Exception
    {
        if (mortesConfirmadas < 0)
            throw new Exception("Mortes confirmadas não podem ser negativas.");
        this.mortesConfirmadas = mortesConfirmadas;
    }

    private float precoPorContrado;
    public float getPrecoPorContrado () { return this.precoPorContrado; }
    public void setPrecoPorContrado (float precoPorContrado) throws Exception
    {
        if (precoPorContrado < 0)
            throw new Exception("O preço por contrado não pode ser negativo.");
        this.precoPorContrado = precoPorContrado;
    }

    private int cep;
    public int getCep () { return this.cep; }
    public void setCep (int cep) throws Exception
    {
        if (cep < 0)
            throw new Exception("CEP não pode ser negativo.");
        this.cep = cep;
    }

    private int numero;
    public int getNumero () { return this.numero; }
    public void setNumero (int numero) throws Exception
    {
        if (numero < 0)
            throw new Exception("Número da casa não poderá ser negativo.");

        this.numero = numero;
    }

    private String complemento;
    public String getComplemento () { return this.complemento; }
    public void setComplemento (String complemento) throws Exception
    {
        if (complemento == null)
            throw new Exception("Complemento não existe.");

        this.complemento = complemento;
    }

    public SerialKiller (String nome,
                         String armas,
                         int vezesContrado,
                         int mortesConfirmadas,
                         float precoPorContrado,
                         int cep,
                         int numero,
                         String complemento) throws Exception
    {
        this.setNome(nome);
        this.setArmas(armas);
        this.setVezesContrado(vezesContrado);
        this.setMortesConfirmadas(mortesConfirmadas);
        this.setPrecoPorContrado(precoPorContrado);
        this.setCep(cep);
        this.setNumero(numero);
        this.setComplemento(complemento);
    }

    public String toString()
    {
        return "Nome: " + this.nome +
                "\nArmas: " + this.armas +
                "\nVezes contrado: " + this.vezesContrado +
                "\nMortes confirmadas: "+ this.mortesConfirmadas +
                "\nPreço por contrado: " + this.precoPorContrado;
                //"\nC.E.P......: "+ this.cep;
    }

    public int hashCode ()
    {
        int ret=1;

        ret = 2*ret + this.nome .hashCode();

        if (this.complemento!=null)
            ret = 2*ret + this.complemento.hashCode();

        ret = 2*ret + this.armas                          .hashCode();
        ret = 2*ret + new Integer(this.vezesContrado)     .hashCode();
        ret = 2*ret + new Integer(this.mortesConfirmadas) .hashCode();
        ret = 2*ret + new Float(this.precoPorContrado)    .hashCode();
        ret = 2*ret + new Integer(this.cep)               .hashCode();
        ret = 2*ret + new Integer(this.numero)            .hashCode();

        return ret;
    }

    public SerialKiller (SerialKiller modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception("Modelo inexistente");

        this.nome = modelo.nome;
        this.armas= modelo.armas;
        this.vezesContrado = modelo.vezesContrado;
        this.mortesConfirmadas = modelo.mortesConfirmadas;
        this.precoPorContrado = modelo.precoPorContrado;
        this.cep = modelo.cep;
        this.numero = modelo.numero;
        this.complemento = modelo.complemento;
    }

    public Object clone ()
    {
        SerialKiller ret = null;

        try
        {
            ret = new SerialKiller(this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}
