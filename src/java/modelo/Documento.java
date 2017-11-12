package modelo;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Documento {
    
    private Integer idDoc;
    private Integer idCliente;
    private String  age;
    private String  cc;
    private String  nomeCli;
    private Long    nossoNumero;
    private String  emissor;
    private Date    dtAgenda;
    private String  dtVcto;
    private Float   valor;
    private String  codigoBarras;
    private Integer motivo;
    

    /**
     * @return the idDoc
     */
    public Integer getIdDoc() {
        return idDoc;
    }

    /**
     * @param idDoc the idDoc to set
     */
    public void setIdDoc(Integer idDoc) {
        this.idDoc = idDoc;
    }

    /**
     * @return the idCliente
     */
    public Integer getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * @param cc the cc to set
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * @return the nomeCli
     */
    public String getNomeCli() {
        return nomeCli;
    }

    /**
     * @param nomeCli the nomeCli to set
     */
    public void setNomeCli(String nomeCli) {
        this.nomeCli = nomeCli;
    }

    /**
     * @return the nossoNumero
     */
    public Long getNossoNumero() {
        return nossoNumero;
    }

    /**
     * @param nossoNumero the nossoNumero to set
     */
    public void setNossoNumero(Long nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    /**
     * @return the emissor
     */
    public String getEmissor() {
        return emissor;
    }

    /**
     * @param emissor the emissor to set
     */
    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }

    /**
     * @return the dtAgenda
     */
    public Date getDtAgenda() {
        return dtAgenda;
    }

    /**
     * @param dtAgenda the dtAgenda to set
     */
    public void setDtAgenda(Date dtAgenda) {
        this.dtAgenda = dtAgenda;
    }

    /**
     * @return the dtVcto
     */
    public String getDtVcto() {
        return dtVcto;
    }

    /**
     * @param dtVcto the dtVcto to set
     */
    public void setDtVcto(String dtVcto) {
        this.dtVcto = dtVcto;
    }

    /**
     * @return the valor
     */
    public Float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Float valor) {
        this.valor = valor;
    }

    /**
     * @return the codigoBarras
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }

    /**
     * @param codigoBarras the codigoBarras to set
     */
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    /**
     * @return the motivo
     */
    public Integer getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(Integer motivo) {
        this.motivo = motivo;
    }

    

   
}
