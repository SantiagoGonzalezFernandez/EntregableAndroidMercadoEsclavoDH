package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;

import com.google.gson.annotations.SerializedName;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.direccionDelVendedor.Geolocation;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.direccionDelVendedor.SellerAddress;

import java.io.Serializable;

public class Producto implements Serializable {

    @SerializedName("id")
    private String myStringId;

    @SerializedName("site_id")
    private String myStringLugarDelId;

    @SerializedName("title")
    private String myStringTitulo;

    @SerializedName("subtitle")
    private String myStringSubtitulo;

    @SerializedName("seller_id")
    private int myIntVendedorId;

    @SerializedName("category_id")
    private String myStringCategoriaId;

    @SerializedName("official_store_id")
    private int myIntIdentificadorDeLaTienda;

    @SerializedName("price")
    private int myIntPrecio;

    @SerializedName("base_price")
    private int myIntPrecioBase;

    @SerializedName("original_price")
    private int myIntPrecioOriginal;

    @SerializedName("currency_id")
    private String myStringMonedaId;

    @SerializedName("initial_quantity")
    private int myIntCantiadadInicial;

    @SerializedName("available_quantity")
    private int myIntCantiadadDisponible;

    @SerializedName("sold_quantity")
    private int myIntCantiadadVendido;

    @SerializedName("buying_mode")
    private String myStringModoDeCompra;

    @SerializedName("listing_type_id")
    private String myStringTipoDeListadoId;

    @SerializedName("start_time")
    private String myStringHoraDeInicio;

    @SerializedName("initial_quantity")
    private String myStringHoraDeFrenado;

    @SerializedName("condition")
    private String myStringCondicion; //Aca lo deberia cambiar por logica asi queda mas lindo (new o used)

    @SerializedName("permalink")
    private String myStringLinkDePublicacion;

    @SerializedName("thumbnail")
    private String myStringFotoMiniatura;

    @SerializedName("secure_thumbnail")
    private String myStringFotoMiniaturaSegura;

    @SerializedName("pictures")
    private Pictures myPictures;

    @SerializedName("video_id")
    private String myStringVideoId;

    @SerializedName("descriptions")
    private DescriptionsProducto myDescriptionsProducto;

    @SerializedName("accepts_mercadopago")
    private Boolean myBooleanAceptaMercadoPago;

    @SerializedName("shipping")
    private Shipping myShipping;

    @SerializedName("international_delivery_mode")
    private String myStringModoDeEntregaInternacional;

    @SerializedName("seller_address")
    private SellerAddress mySellerAddress;

    @SerializedName("seller_contact")
    private String myStringContactoVendedor;

    @SerializedName("geolocation")
    private Geolocation myGeolocation;

    @SerializedName("attributes")
    private Attributes myAttributes;

    @SerializedName("status")
    private String myStringEstado;

    @SerializedName("warranty")
    private String myStringGarantia;

    @SerializedName("domain_id")
    private String myStringDominioId;

    @SerializedName("date_created")
    private String myStringFechaDeCreacion;

    @SerializedName("last_updated")
    private String myStringUltimaActualizacion;

    @SerializedName("health")
    private Float myFloatSalud;

    public Producto(String myStringId, String myStringLugarDelId, String myStringTitulo, String myStringSubtitulo, int myIntVendedorId, String myStringCategoriaId, int myIntIdentificadorDeLaTienda, int myIntPrecio, int myIntPrecioBase, int myIntPrecioOriginal, String myStringMonedaId, int myIntCantiadadInicial, int myIntCantiadadDisponible, int myIntCantiadadVendido, String myStringModoDeCompra, String myStringTipoDeListadoId, String myStringHoraDeInicio, String myStringHoraDeFrenado, String myStringCondicion, String myStringLinkDePublicacion, String myStringFotoMiniatura, String myStringFotoMiniaturaSegura, Pictures myPictures, String myStringVideoId, DescriptionsProducto myDescriptionsProducto, Boolean myBooleanAceptaMercadoPago, Shipping myShipping, String myStringModoDeEntregaInternacional, SellerAddress mySellerAddress, String myStringContactoVendedor, Geolocation myGeolocation, Attributes myAttributes, String myStringEstado, String myStringGarantia, String myStringDominioId, String myStringFechaDeCreacion, String myStringUltimaActualizacion, Float myFloatSalud) {
        this.myStringId = myStringId;
        this.myStringLugarDelId = myStringLugarDelId;
        this.myStringTitulo = myStringTitulo;
        this.myStringSubtitulo = myStringSubtitulo;
        this.myIntVendedorId = myIntVendedorId;
        this.myStringCategoriaId = myStringCategoriaId;
        this.myIntIdentificadorDeLaTienda = myIntIdentificadorDeLaTienda;
        this.myIntPrecio = myIntPrecio;
        this.myIntPrecioBase = myIntPrecioBase;
        this.myIntPrecioOriginal = myIntPrecioOriginal;
        this.myStringMonedaId = myStringMonedaId;
        this.myIntCantiadadInicial = myIntCantiadadInicial;
        this.myIntCantiadadDisponible = myIntCantiadadDisponible;
        this.myIntCantiadadVendido = myIntCantiadadVendido;
        this.myStringModoDeCompra = myStringModoDeCompra;
        this.myStringTipoDeListadoId = myStringTipoDeListadoId;
        this.myStringHoraDeInicio = myStringHoraDeInicio;
        this.myStringHoraDeFrenado = myStringHoraDeFrenado;
        this.myStringCondicion = myStringCondicion;
        this.myStringLinkDePublicacion = myStringLinkDePublicacion;
        this.myStringFotoMiniatura = myStringFotoMiniatura;
        this.myStringFotoMiniaturaSegura = myStringFotoMiniaturaSegura;
        this.myPictures = myPictures;
        this.myStringVideoId = myStringVideoId;
        this.myDescriptionsProducto = myDescriptionsProducto;
        this.myBooleanAceptaMercadoPago = myBooleanAceptaMercadoPago;
        this.myShipping = myShipping;
        this.myStringModoDeEntregaInternacional = myStringModoDeEntregaInternacional;
        this.mySellerAddress = mySellerAddress;
        this.myStringContactoVendedor = myStringContactoVendedor;
        this.myGeolocation = myGeolocation;
        this.myAttributes = myAttributes;
        this.myStringEstado = myStringEstado;
        this.myStringGarantia = myStringGarantia;
        this.myStringDominioId = myStringDominioId;
        this.myStringFechaDeCreacion = myStringFechaDeCreacion;
        this.myStringUltimaActualizacion = myStringUltimaActualizacion;
        this.myFloatSalud = myFloatSalud;
    }

    public Producto() {
    }

    public String getMyStringId() {
        return myStringId;
    }

    public void setMyStringId(String myStringId) {
        this.myStringId = myStringId;
    }

    public String getMyStringLugarDelId() {
        return myStringLugarDelId;
    }

    public void setMyStringLugarDelId(String myStringLugarDelId) {
        this.myStringLugarDelId = myStringLugarDelId;
    }

    public String getMyStringTitulo() {
        return myStringTitulo;
    }

    public void setMyStringTitulo(String myStringTitulo) {
        this.myStringTitulo = myStringTitulo;
    }

    public String getMyStringSubtitulo() {
        return myStringSubtitulo;
    }

    public void setMyStringSubtitulo(String myStringSubtitulo) {
        this.myStringSubtitulo = myStringSubtitulo;
    }

    public int getMyIntVendedorId() {
        return myIntVendedorId;
    }

    public void setMyIntVendedorId(int myIntVendedorId) {
        this.myIntVendedorId = myIntVendedorId;
    }

    public String getMyStringCategoriaId() {
        return myStringCategoriaId;
    }

    public void setMyStringCategoriaId(String myStringCategoriaId) {
        this.myStringCategoriaId = myStringCategoriaId;
    }

    public int getMyIntIdentificadorDeLaTienda() {
        return myIntIdentificadorDeLaTienda;
    }

    public void setMyIntIdentificadorDeLaTienda(int myIntIdentificadorDeLaTienda) {
        this.myIntIdentificadorDeLaTienda = myIntIdentificadorDeLaTienda;
    }

    public int getMyIntPrecio() {
        return myIntPrecio;
    }

    public void setMyIntPrecio(int myIntPrecio) {
        this.myIntPrecio = myIntPrecio;
    }

    public int getMyIntPrecioBase() {
        return myIntPrecioBase;
    }

    public void setMyIntPrecioBase(int myIntPrecioBase) {
        this.myIntPrecioBase = myIntPrecioBase;
    }

    public int getMyIntPrecioOriginal() {
        return myIntPrecioOriginal;
    }

    public void setMyIntPrecioOriginal(int myIntPrecioOriginal) {
        this.myIntPrecioOriginal = myIntPrecioOriginal;
    }

    public String getMyStringMonedaId() {
        return myStringMonedaId;
    }

    public void setMyStringMonedaId(String myStringMonedaId) {
        this.myStringMonedaId = myStringMonedaId;
    }

    public int getMyIntCantiadadInicial() {
        return myIntCantiadadInicial;
    }

    public void setMyIntCantiadadInicial(int myIntCantiadadInicial) {
        this.myIntCantiadadInicial = myIntCantiadadInicial;
    }

    public int getMyIntCantiadadDisponible() {
        return myIntCantiadadDisponible;
    }

    public void setMyIntCantiadadDisponible(int myIntCantiadadDisponible) {
        this.myIntCantiadadDisponible = myIntCantiadadDisponible;
    }

    public int getMyIntCantiadadVendido() {
        return myIntCantiadadVendido;
    }

    public void setMyIntCantiadadVendido(int myIntCantiadadVendido) {
        this.myIntCantiadadVendido = myIntCantiadadVendido;
    }

    public String getMyStringModoDeCompra() {
        return myStringModoDeCompra;
    }

    public void setMyStringModoDeCompra(String myStringModoDeCompra) {
        this.myStringModoDeCompra = myStringModoDeCompra;
    }

    public String getMyStringTipoDeListadoId() {
        return myStringTipoDeListadoId;
    }

    public void setMyStringTipoDeListadoId(String myStringTipoDeListadoId) {
        this.myStringTipoDeListadoId = myStringTipoDeListadoId;
    }

    public String getMyStringHoraDeInicio() {
        return myStringHoraDeInicio;
    }

    public void setMyStringHoraDeInicio(String myStringHoraDeInicio) {
        this.myStringHoraDeInicio = myStringHoraDeInicio;
    }

    public String getMyStringHoraDeFrenado() {
        return myStringHoraDeFrenado;
    }

    public void setMyStringHoraDeFrenado(String myStringHoraDeFrenado) {
        this.myStringHoraDeFrenado = myStringHoraDeFrenado;
    }

    public String getMyStringCondicion() {
        return myStringCondicion;
    }

    public void setMyStringCondicion(String myStringCondicion) {
        this.myStringCondicion = myStringCondicion;
    }

    public String getMyStringLinkDePublicacion() {
        return myStringLinkDePublicacion;
    }

    public void setMyStringLinkDePublicacion(String myStringLinkDePublicacion) {
        this.myStringLinkDePublicacion = myStringLinkDePublicacion;
    }

    public String getMyStringFotoMiniatura() {
        return myStringFotoMiniatura;
    }

    public void setMyStringFotoMiniatura(String myStringFotoMiniatura) {
        this.myStringFotoMiniatura = myStringFotoMiniatura;
    }

    public String getMyStringFotoMiniaturaSegura() {
        return myStringFotoMiniaturaSegura;
    }

    public void setMyStringFotoMiniaturaSegura(String myStringFotoMiniaturaSegura) {
        this.myStringFotoMiniaturaSegura = myStringFotoMiniaturaSegura;
    }

    public Pictures getMyPictures() {
        return myPictures;
    }

    public void setMyPictures(Pictures myPictures) {
        this.myPictures = myPictures;
    }

    public String getMyStringVideoId() {
        return myStringVideoId;
    }

    public void setMyStringVideoId(String myStringVideoId) {
        this.myStringVideoId = myStringVideoId;
    }

    public DescriptionsProducto getMyDescriptionsProducto() {
        return myDescriptionsProducto;
    }

    public void setMyDescriptionsProducto(DescriptionsProducto myDescriptionsProducto) {
        this.myDescriptionsProducto = myDescriptionsProducto;
    }

    public Boolean getMyBooleanAceptaMercadoPago() {
        return myBooleanAceptaMercadoPago;
    }

    public void setMyBooleanAceptaMercadoPago(Boolean myBooleanAceptaMercadoPago) {
        this.myBooleanAceptaMercadoPago = myBooleanAceptaMercadoPago;
    }

    public Shipping getMyShipping() {
        return myShipping;
    }

    public void setMyShipping(Shipping myShipping) {
        this.myShipping = myShipping;
    }

    public String getMyStringModoDeEntregaInternacional() {
        return myStringModoDeEntregaInternacional;
    }

    public void setMyStringModoDeEntregaInternacional(String myStringModoDeEntregaInternacional) {
        this.myStringModoDeEntregaInternacional = myStringModoDeEntregaInternacional;
    }

    public SellerAddress getMySellerAddress() {
        return mySellerAddress;
    }

    public void setMySellerAddress(SellerAddress mySellerAddress) {
        this.mySellerAddress = mySellerAddress;
    }

    public String getMyStringContactoVendedor() {
        return myStringContactoVendedor;
    }

    public void setMyStringContactoVendedor(String myStringContactoVendedor) {
        this.myStringContactoVendedor = myStringContactoVendedor;
    }

    public Geolocation getMyGeolocation() {
        return myGeolocation;
    }

    public void setMyGeolocation(Geolocation myGeolocation) {
        this.myGeolocation = myGeolocation;
    }

    public Attributes getMyAttributes() {
        return myAttributes;
    }

    public void setMyAttributes(Attributes myAttributes) {
        this.myAttributes = myAttributes;
    }

    public String getMyStringEstado() {
        return myStringEstado;
    }

    public void setMyStringEstado(String myStringEstado) {
        this.myStringEstado = myStringEstado;
    }

    public String getMyStringGarantia() {
        return myStringGarantia;
    }

    public void setMyStringGarantia(String myStringGarantia) {
        this.myStringGarantia = myStringGarantia;
    }

    public String getMyStringDominioId() {
        return myStringDominioId;
    }

    public void setMyStringDominioId(String myStringDominioId) {
        this.myStringDominioId = myStringDominioId;
    }

    public String getMyStringFechaDeCreacion() {
        return myStringFechaDeCreacion;
    }

    public void setMyStringFechaDeCreacion(String myStringFechaDeCreacion) {
        this.myStringFechaDeCreacion = myStringFechaDeCreacion;
    }

    public String getMyStringUltimaActualizacion() {
        return myStringUltimaActualizacion;
    }

    public void setMyStringUltimaActualizacion(String myStringUltimaActualizacion) {
        this.myStringUltimaActualizacion = myStringUltimaActualizacion;
    }

    public Float getMyFloatSalud() {
        return myFloatSalud;
    }

    public void setMyFloatSalud(Float myFloatSalud) {
        this.myFloatSalud = myFloatSalud;
    }
}
