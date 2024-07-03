import React, {useState,useEffect} from 'react';
import {Link} from 'react-router-dom';
import repairService from '../services/repair.service';
import {Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Box, Button, TextField, Grid, dividerClasses} from '@mui/material';

const RepairHistory = () => {


    /* repair history code */

    const [repairs, setRepairs] = useState([]);

    const fetchRepairs = async () => {
        try {
          const response = await repairService.getAllRepairs();
          setRepairs(response.data);
        } catch (error) {
          console.error('Error al obtener las reparaciones:', error);
        }
      };

    useEffect(() => {
        fetchRepairs();
      }, []);

    console.log(repairs);
    

    return ( 
        <div>
            <h1><b>[repair history list]</b></h1>
            <div style={{ backgroundColor: 'white', color: 'black', padding: '20px', borderRadius: '10px' }}>
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell><b>Vehicle Plate</b></TableCell>
                                <TableCell><b>Vehicle Brand</b></TableCell>
                                <TableCell><b>Vehicle Model</b></TableCell>
                                <TableCell><b>Vehicle Type</b></TableCell>
                                <TableCell><b>Fabrication Year</b></TableCell>
                                <TableCell><b>Engine Type</b></TableCell>
                                <TableCell><b>EntryVDate</b></TableCell>
                                <TableCell><b>EntryVTime</b></TableCell>
                                <TableCell><b>Total Cost</b></TableCell>
                                <TableCell><b>Total Surcharge</b></TableCell>
                                <TableCell><b>Total Discount</b></TableCell>
                                <TableCell><b>Amount Iva</b></TableCell>
                                <TableCell><b>Final Price</b></TableCell>
                                <TableCell><b>ExitVDate</b></TableCell>
                                <TableCell><b>ExitVTime</b></TableCell>
                                <TableCell><b>ExitCDate</b></TableCell>
                                <TableCell><b>ExitCTime</b></TableCell>
                            </TableRow>
                        </TableHead>
                            
                        <TableBody>
                            {repairs.map((repair) => (
                                <TableRow key={repair.id}>
                                    <TableCell>{repair.vehiclePlate}</TableCell>
                                    <TableCell>{repair.vehicleBrand}</TableCell>
                                    <TableCell>{repair.vehicleModel}</TableCell>
                                    <TableCell>{repair.vehicleType}</TableCell>
                                    <TableCell>{repair.fabricationYear}</TableCell>
                                    <TableCell>{repair.engineType}</TableCell>
                                    <TableCell>{repair.entryVDate}</TableCell>
                                    <TableCell>{repair.entryVTime}</TableCell>
                                    <TableCell>{repair.totalCost}</TableCell>
                                    <TableCell>{repair.totalSurcharge}</TableCell>
                                    <TableCell>{repair.total_discount}</TableCell>
                                    <TableCell>{repair.amount_iva}</TableCell>
                                    <TableCell>{repair.finalPrice}</TableCell>
                                    <TableCell>{repair.exitVDate}</TableCell>
                                    <TableCell>{repair.exitVTime}</TableCell>
                                    <TableCell>{repair.exitCDate}</TableCell>
                                    <TableCell>{repair.exitCTime}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        </div>
    );
}

export default RepairHistory;