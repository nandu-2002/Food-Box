import React from "react";
import HomeIcon from '@mui/icons-material/Home';
import { Button, Card } from "@mui/material";

export const AddressCard=({item,showButton,handleSelectAddress})=>{
   
    return(
        <Card className="flex gap-5 w-50 p-4">
            <HomeIcon/>
            <div className="space-y-3 text-gray-500">
                <h1 className="font-semibold text-lg text-white">Home</h1>
                <p>
                    {item.deliveryAddress.streetAddress},{item.deliveryAddress.city},{item.deliveryAddress.stateProvince},{item.deliveryAddress.country},
                    <p>{item.deliveryAddress.postalCode}</p>
                </p>
                {showButton && (
                    <Button variant="outlined" fullWidth onClick={()=>handleSelectAddress()}>select</Button>)}

            </div>
            
        </Card>
    )
}