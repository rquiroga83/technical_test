import React, { Component } from 'react';
import { Navbar } from "react-bootstrap";
import './App.css';
import TableFetch from "./components/TableFetch"

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="app-header">
          <Navbar fluid>
            <Navbar.Header>
              <Navbar.Brand>
                Cliente Rest Prueba Tecnica
              </Navbar.Brand>
            </Navbar.Header>
          </Navbar>
        </header>
        <div className="content">
          <TableFetch/>
        </div>
      </div>
    );
  }
}

export default App;
