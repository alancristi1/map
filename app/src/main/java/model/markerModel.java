package model;

/**
 * Created by alan.resende on 06/02/2018.
 */

public class markerModel {

    private double latitude;
    private double longitude;
    private String nome;
    private String codigo;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;

    public markerModel(double latitude, double longitude, String nome, String codigo ,String endereco,
                       String numero, String bairro, String cidade, String uf) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nome = nome;
        this.codigo = codigo;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", nome='" + nome + '\'' +
                ", codigo='" + codigo + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }

    public markerModel(double latitude, double longitude, String nome, String codigo,
                       String endereco) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nome = nome;
        this.codigo = codigo;
        this.endereco = endereco;
    }

    public markerModel(double latitude, double longitude, String nome, String endereco) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nome = nome;
        this.endereco = endereco;
    }

    public markerModel(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}
