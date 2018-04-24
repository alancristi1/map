package model;

/**
 * Created by alan.resende on 05/02/2018.
 */

public class clientesModel {

    private double latitude;
    private double longitude;
    private String nomePonto;
    private String codigoCliente;
    private long cdPontoEntidade;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private int tipoPonto;

    public clientesModel(double latitude, double longitude, String nomePonto,
                         String codigoCliente, long cdPontoEntidade, String endereco, String numero,
                         String bairro, String cidade, String uf, int tipoPonto) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nomePonto = nomePonto;
        this.codigoCliente = codigoCliente;
        this.cdPontoEntidade = cdPontoEntidade;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.tipoPonto = tipoPonto;
    }

    public clientesModel(String nome, String endereco) {
        this.nomePonto = nome;
        this.endereco = endereco;
    }

    public clientesModel(double lat, double longi, String codigoCliente){
        this.latitude = lat;
        this.longitude = longi;
        this.codigoCliente = codigoCliente;
    }

    public clientesModel(double lat, double longi){
        this.latitude = lat;
        this.longitude = longi;
    }

    public clientesModel(String endereco, String numero, String bairro, String cidade, String uf) {
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public clientesModel(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    @Override
    public String toString() {
        return "{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
//                ", nomePonto='" + nomePonto + '\'' +
                ", codigoCliente=" + codigoCliente +
//                ", cdPontoEntidade=" + cdPontoEntidade +
//                ", endereco='" + endereco + '\'' +
//                ", numero='" + numero + '\'' +
//                ", bairro='" + bairro + '\'' +
//                ", cidade='" + cidade + '\'' +
//                ", uf='" + uf + '\'' +
//                ", tipoPonto=" + tipoPonto +
                '}';
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNomePonto() {
        return nomePonto;
    }

    public void setNomePonto(String nomePonto) {
        this.nomePonto = nomePonto;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public long getCdPontoEntidade() {
        return cdPontoEntidade;
    }

    public void setCdPontoEntidade(int cdPontoEntidade) {
        this.cdPontoEntidade = cdPontoEntidade;
    }

    public int getTipoPonto() {
        return tipoPonto;
    }

    public void setTipoPonto(int tipoPonto) {
        this.tipoPonto = tipoPonto;
    }
}
