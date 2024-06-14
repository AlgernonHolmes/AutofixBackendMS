import React, { useState, useEffect } from 'react';
import repairService from "../services/repair.service";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';


const R2 = () => {
    const [data, setData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const result = await repairService.getR2();
            if (Array.isArray(result.data)) {
                setData(result.data);
            } else {
                console.error('El resultado de getR2 no es un array:', result);
            }
        };
        fetchData(); 
    }, []);

    return (
        <div style={{ padding: '20px' }}>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell><b>Numero reparacion</b></TableCell>
                            <TableCell><b>Sedan</b></TableCell>
                            <TableCell><b>Hatchback</b></TableCell>
                            <TableCell><b>SUV</b></TableCell>
                            <TableCell><b>Pickup</b></TableCell>
                            <TableCell><b>Furgoneta</b></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {data.map((row, index) => (
                            <TableRow key={index}>
                                <TableCell>{index + 1}</TableCell>
                                <TableCell>{row[0]}</TableCell>
                                <TableCell>{row[1]}</TableCell>
                                <TableCell>{row[2]}</TableCell>
                                <TableCell>{row[3]}</TableCell>
                                <TableCell>{row[4]}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
};

export default R2;


