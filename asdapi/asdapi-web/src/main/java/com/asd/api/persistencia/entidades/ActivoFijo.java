/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.persistencia.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "activo_fijo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivoFijo.findAll", query = "SELECT a FROM ActivoFijo a")
    , @NamedQuery(name = "ActivoFijo.findById", query = "SELECT a FROM ActivoFijo a WHERE a.id = :id")
    , @NamedQuery(name = "ActivoFijo.findByNombre", query = "SELECT a FROM ActivoFijo a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "ActivoFijo.findByDescripcion", query = "SELECT a FROM ActivoFijo a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "ActivoFijo.findBySerial", query = "SELECT a FROM ActivoFijo a WHERE a.serial = :serial")
    , @NamedQuery(name = "ActivoFijo.findByNumeroInternoInventario", query = "SELECT a FROM ActivoFijo a WHERE a.numeroInternoInventario = :numeroInternoInventario")
    , @NamedQuery(name = "ActivoFijo.findByPeso", query = "SELECT a FROM ActivoFijo a WHERE a.peso = :peso")
    , @NamedQuery(name = "ActivoFijo.findByAlto", query = "SELECT a FROM ActivoFijo a WHERE a.alto = :alto")
    , @NamedQuery(name = "ActivoFijo.findByAncho", query = "SELECT a FROM ActivoFijo a WHERE a.ancho = :ancho")
    , @NamedQuery(name = "ActivoFijo.findByLargo", query = "SELECT a FROM ActivoFijo a WHERE a.largo = :largo")
    , @NamedQuery(name = "ActivoFijo.findByValorCompra", query = "SELECT a FROM ActivoFijo a WHERE a.valorCompra = :valorCompra")
    , @NamedQuery(name = "ActivoFijo.findByFechaCompra", query = "SELECT a FROM ActivoFijo a WHERE a.fechaCompra = :fechaCompra")
    , @NamedQuery(name = "ActivoFijo.findByFechaBaja", query = "SELECT a FROM ActivoFijo a WHERE a.fechaBaja = :fechaBaja")
    , @NamedQuery(name = "ActivoFijo.findByTipo", query = "SELECT a FROM ActivoFijo a WHERE a.tipoId.id = :idTipo")})
public class ActivoFijo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "serial")
    private String serial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "numero_interno_inventario")
    private String numeroInternoInventario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso")
    private Double peso;
    @Column(name = "alto")
    private Double alto;
    @Column(name = "ancho")
    private Double ancho;
    @Column(name = "largo")
    private Double largo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_compra")
    private long valorCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_compra")
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @JoinTable(name = "activo_fijo_persona", joinColumns = {
        @JoinColumn(name = "activo_fijo_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "persona_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Persona> personaCollection;
    @JoinTable(name = "activo_fijo_area", joinColumns = {
        @JoinColumn(name = "activo_fijo_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "area_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Area> areaCollection;
    @JoinColumn(name = "estado_actual_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EstadoActual estadoActualId;
    @JoinColumn(name = "tipo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tipo tipoId;

    public ActivoFijo() {
    }

    public ActivoFijo(Integer id) {
        this.id = id;
    }

    public ActivoFijo(Integer id, String nombre, String descripcion, String serial, String numeroInternoInventario, long valorCompra, Date fechaCompra, Date fechaBaja) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.serial = serial;
        this.numeroInternoInventario = numeroInternoInventario;
        this.valorCompra = valorCompra;
        this.fechaCompra = fechaCompra;
        this.fechaBaja = fechaBaja;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNumeroInternoInventario() {
        return numeroInternoInventario;
    }

    public void setNumeroInternoInventario(String numeroInternoInventario) {
        this.numeroInternoInventario = numeroInternoInventario;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAlto() {
        return alto;
    }

    public void setAlto(Double alto) {
        this.alto = alto;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public Double getLargo() {
        return largo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }

    public long getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(long valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    @XmlTransient
    public Collection<Area> getAreaCollection() {
        return areaCollection;
    }

    public void setAreaCollection(Collection<Area> areaCollection) {
        this.areaCollection = areaCollection;
    }

    public EstadoActual getEstadoActualId() {
        return estadoActualId;
    }

    public void setEstadoActualId(EstadoActual estadoActualId) {
        this.estadoActualId = estadoActualId;
    }

    public Tipo getTipoId() {
        return tipoId;
    }

    public void setTipoId(Tipo tipoId) {
        this.tipoId = tipoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivoFijo)) {
            return false;
        }
        ActivoFijo other = (ActivoFijo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asd.api.persistencia.entidades.ActivoFijo[ id=" + id + " ]";
    }
    
}
