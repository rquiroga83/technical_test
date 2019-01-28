import React, { Component } from 'react';
import { Table, Button } from "react-bootstrap";


const wellStyles = { maxWidth: 400, margin: '0 auto 10px' };

class TableFetch extends Component {

    constructor() {
        super();

        // State
        this.state = {
            activos: []
        };
    }


    getActivos() {
        fetch(`http://localhost:8080/asd/services/servicioActivos/obtenerActivosFijos`)
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                this.setState({ activos: data.activosFijos })
            })
            .catch((err) => {
                console.log('Error Fetch Api :-S', err);
            });
    }


    componentDidMount() {
        this.getActivos();
    }

    render() {

        let table_content = <tr />;
        if (this.state.activos.length > 0) {
            table_content = this.state.activos.map((activos_row, id) => {
                return (
                    <tr key={id} >
                        <td >{activos_row.id}</td>
                        <td >{activos_row.nombre}</td>
                        <td >{activos_row.numeroInternoInventario}</td>
                        <td >{activos_row.serial}</td>
                        <td >{activos_row.tipoId.nombre}</td>
                        <td >{activos_row.fechaBaja}</td>
                        <td >{activos_row.fechaCompra}</td>
                    </tr>
                );
            });
        }

        return (
            <div>
                <div className="well" style={wellStyles}>
                    <Button bsStyle="primary" bsSize="large" block onClick={()=>{this.getActivos()}}>
                        Actualizar
                    </Button>
                </div>
                <Table hover>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>NOMBRE</th>
                            <th>NUMERO INVENTARIO</th>
                            <th>SERIAL</th>
                            <th>TIPO</th>
                            <th>FECHA BAJA</th>
                            <th>FECHA COMPRA</th>
                        </tr>
                    </thead>
                    <tbody>
                        {table_content}
                    </tbody>
                </Table>
            </div>
        );
    }

}


export default TableFetch;