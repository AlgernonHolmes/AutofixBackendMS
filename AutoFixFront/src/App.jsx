
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './components/Home';
import VehicleForm from './components/VehicleForm';
import VehicleList from './components/VehicleList';
import RepairForm from './components/RepairForm';
import RepairList from './components/RepairList';
import VehicleReceipt from './components/VehicleReceipt';
import Navbar from './components/Navbar';
import R1 from './components/R1';
import R2 from './components/R2';
import R3 from './components/R3';
import R4 from './components/R4';

function App() {
  return (
    <Router>
      <div className="container">
        <Routes>
          <Route path="/" element={<Home />} /> 
          <Route path="/home" element={<Home />} />
          <Route path="/add" element={<VehicleForm/>}></Route>
          <Route path="/list" element={<VehicleList/>}></Route>
          <Route path="/add/repair" element={<RepairForm/>}></Route>
          <Route path="/list/repair" element={<RepairList/>}></Route>
          <Route path="/receipt" element={<VehicleReceipt/>}></Route>
          <Route path="/r1" element={<R1/>}></Route>
          <Route path="/r2" element={<R2/>}></Route>
          <Route path="/r3" element={<R3/>}></Route>
          <Route path="/r4" element={<R4/>}></Route>
        </Routes>
      </div>
      <Navbar />
    </Router>
  );
}

export default App;
