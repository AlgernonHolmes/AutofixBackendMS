import React, { useState, useEffect } from 'react';
import repairService from "../services/repair.service";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';

const R4 = () => {
    const [data, setData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const result = await repairService.getR4();
                setData(result.data);
            } catch (error) {
                console.error('Error al obtener los datos de R4:', error);
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
                            <TableCell><b>Gasolina</b></TableCell>
                            <TableCell><b>Diesel</b></TableCell>
                            <TableCell><b>Hibrido</b></TableCell>
                            <TableCell><b>Electrico</b></TableCell>
                            <TableCell><b>Monto Total</b></TableCell>
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

export default R4;
