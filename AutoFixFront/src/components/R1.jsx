import React, {useState, useEffect} from 'react';
import receiptService from '../services/receipt.service';
import {Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Box, Button, TextField, Grid} from '@mui/material';


const R1 = () => {

    const[receipts, setReceipts] = useState([]);

    useEffect(() => {
        const getReceipts = async () => {
            try {
                const response = await receiptService.getAllReceipts();
                setReceipts(response.data);
            } catch (error) {
                console.error('Error al obtener las reparaciones:', error);
            }
        };

        getReceipts();
    }, [])


    return(
        <div style={{ backgroundColor: 'white', color: 'black', padding: '20px', borderRadius: '10px' }}>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell><b>Vehicle plate</b></TableCell>
                            <TableCell><b>Total cost</b></TableCell>
                            <TableCell><b>Total surcharge</b></TableCell>
                            <TableCell><b>Total discount</b></TableCell>
                            <TableCell><b>Coupon assigned</b></TableCell>
                            <TableCell><b>IVA</b></TableCell>
                            <TableCell><b>Real cost</b></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {receipts.map((receipt) =>  (
                            <TableRow key= {receipt.id}>
                                <TableCell>{receipt.vehiclePlate || "No data avilable"}</TableCell>
                                <TableCell>{receipt.totalPayment || "No data avilable"}</TableCell>
                                <TableCell>{receipt.totalSurcharge || "No data avilable"}</TableCell>
                                <TableCell>{receipt.totalDiscount || "No data avilable"}</TableCell>
                                <TableCell>{receipt.couponAssigned || "No coupon assigned"}</TableCell>
                                <TableCell>{( (receipt.totalPayment + receipt.totalSurcharge - receipt.totalDiscount) * 0.19) || "No data available"}</TableCell>
                                <TableCell>{receipt.realPayment || "No data avilable"}</TableCell>
                            </TableRow>
                        )
                        
                        )}
                    </TableBody>
                </Table>
            </TableContainer>

        </div>
    );
}

export default R1;