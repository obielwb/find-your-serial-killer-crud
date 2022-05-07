package com.company.bd.dbos;

public class SerialKiller {
    private String id;
    public String getId () { return this.id; }
    public void setId (String id) throws Exception
    {
        if (id == null)
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

    private int vezesContratado;
    public int getVezesContratado() { return this.vezesContratado; }
    public void setVezesContratado(int vezesContratado) throws Exception
    {
        if (vezesContratado < 0)
            throw new Exception("O número de vezes contrato não pode ser negativo.");
        this.vezesContratado = vezesContratado;
    }

    private int mortesConfirmadas;
    public int getMortesConfirmadas () { return this.mortesConfirmadas; }
    public void setMortesConfirmadas (int mortesConfirmadas) throws Exception
    {
        if (mortesConfirmadas < 0)
            throw new Exception("Mortes confirmadas não podem ser negativas.");
        this.mortesConfirmadas = mortesConfirmadas;
    }

    private float precoPorContrato;
    public float getPrecoPorContrato() { return this.precoPorContrato; }
    public void setPrecoPorContrato(float precoPorContrato) throws Exception
    {
        if (precoPorContrato < 0)
            throw new Exception("O preço por contrato não pode ser negativo.");
        this.precoPorContrato = precoPorContrato;
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

    public SerialKiller (String id,
                         String nome,
                         String armas,
                         int vezesContratado,
                         int mortesConfirmadas,
                         float precoPorContrato,
                         int cep,
                         int numero,
                         String complemento) throws Exception
    {
        this.setId(id);
        this.setNome(nome);
        this.setArmas(armas);
        this.setVezesContratado(vezesContratado);
        this.setMortesConfirmadas(mortesConfirmadas);
        this.setPrecoPorContrato(precoPorContrato);
        this.setCep(cep);
        this.setNumero(numero);
        this.setComplemento(complemento);
    }

    public String toString()
    {
        return  "ID................: " + this.id +
                "\nNome..............: " + this.nome +
                "\nArmas.............: " + this.armas +
                "\nVezes contrato:...: " + this.vezesContratado +
                "\nMortes confirmadas: " + this.mortesConfirmadas +
                "\nPreço por contrato: " + this.precoPorContrato +
                "\nC.E.P.............: " + this.cep +
                "\nNumero............: " + this.numero +
                "\nComplemento.......: " + this.complemento;
    }

    public int hashCode ()
    {
        int ret=1;

        ret = 2*ret + this.nome .hashCode();

        if (this.complemento!=null)
            ret = 2*ret + this.complemento.hashCode();

        ret = 2*ret + this.id                             .hashCode();
        ret = 2*ret + this.armas                          .hashCode();
        ret = 2*ret + new Integer(this.vezesContratado)   .hashCode();
        ret = 2*ret + new Integer(this.mortesConfirmadas) .hashCode();
        ret = 2*ret + new Float(this.precoPorContrato)    .hashCode();
        ret = 2*ret + new Integer(this.cep)               .hashCode();
        ret = 2*ret + new Integer(this.numero)            .hashCode();

        return ret;
    }

    public SerialKiller (SerialKiller modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception("Modelo inexistente");

        this.id = modelo.id;
        this.nome = modelo.nome;
        this.armas= modelo.armas;
        this.vezesContratado = modelo.vezesContratado;
        this.mortesConfirmadas = modelo.mortesConfirmadas;
        this.precoPorContrato = modelo.precoPorContrato;
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
