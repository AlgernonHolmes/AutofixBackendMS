import React, {useState,useEffect} from 'react';
import {Link} from 'react-router-dom';
import repairService from '../services/repair.service';
import {Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Box, Button, TextField, Grid} from '@mui/material';

const RepairList = () => {
    const [repairs, setRepairs] = useState([]);

    useEffect(() => {
        fetchRepairs();
      }, []);


    const fetchRepairs = async () => {
        try {
          const response = await repairService.getAllRepairDetails();
          setRepairs(response.data);
        } catch (error) {
          console.error('Error al obtener las reparaciones:', error);
        }
      };

      const handleDeleteRepair = (id) => {
        repairService.deleteRepairDetailById(id);
      }
    


      const handleUpdateRepairHistory = (repairs) => {
    
        const uniqueVehiclePlates = {};

        repairs.forEach((repair) => {
          const { vehiclePlate } = repair;
          if (!uniqueVehiclePlates[vehiclePlate]) {
            uniqueVehiclePlates[vehiclePlate] = { vehiclePlate };
          }
        });
        
        const uniqueRepairObjects = Object.values(uniqueVehiclePlates);
        console.log(uniqueRepairObjects)
        uniqueRepairObjects.forEach((repairObject) => {
          repairService.createRepair(repairObject);
        });
      }
      
      
      
    return (   
        <div>
            <h1><b>[repair detail list]</b></h1>
            <div style={{ backgroundColor: 'white', color: 'black', padding: '20px', borderRadius: '10px' }}>
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell><b>Vehicle Plate</b></TableCell>
                                <TableCell><b>Repair Type</b></TableCell>
                                <TableCell><b>Repair Date</b></TableCell>
                                <TableCell><b>Repair Time</b></TableCell>
                                <TableCell><b>Repair Cost</b></TableCell>
                                <TableCell><b>Update Repair</b></TableCell>
                                <TableCell><b>Delete Repair</b></TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {repairs.map((repair) => (
                                <TableRow key={repair.id}>
                                    <TableCell>{repair.vehiclePlate}</TableCell>
                                    <TableCell>{repair.repairType}</TableCell>
                                    <TableCell>{repair.repairDate}</TableCell>
                                    <TableCell>{repair.repairTime}</TableCell>
                                    <TableCell>{repair.repairCost}</TableCell>
                                    <TableCell>
                                        <Button variant="contained" style={{ backgroundColor: '#800000', color: 'white' }} onClick={() => handleUpdateRepair(repair.id)}>Update</Button>
                                    </TableCell>
                                    <TableCell>
                                        <Button variant="contained" style={{ backgroundColor: '#800000', color: 'white' }} onClick={() => handleDeleteRepair(repair.id)}>Delete</Button>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>

                <Box mt={2}>
                    <Button component={Link} to="/add/repair" variant="contained" style={{ backgroundColor: '#800000', color: 'white' }}>Add Repair</Button>
                </Box>

                <Box mt={2}>
                    <Button component={Link} variant="contained" style={{ backgroundColor: '#800000', color: 'white' }} 
                            onClick={() => handleUpdateRepairHistory(repairs)} >Update Repair History</Button>
                </Box>
            </div>
        </div>
    );
};

export default RepairList;







