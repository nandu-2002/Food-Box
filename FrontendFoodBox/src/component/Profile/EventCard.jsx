import { Card, CardActions, CardContent, CardMedia, IconButton, Typography } from "@mui/material";
import React from "react";
import DeleteIcon from '@mui/icons-material/Delete';

export const EventCard = ({ item }) => {
    // Destructuring item to access its properties
    const { image, name, location, startDateTime, endDateTime } = item;

    // Safely access imageUrl[0] (check if imageUrl exists and has at least one element)
    const imageSrc = (image && Array.isArray(image) && image[0]) || 'default-image.jpg'; // Fallback image

    return (
        <div>
            <Card sx={{ width: 300 }}>
                {/* Use the fallback image if imageUrl is null or undefined */}
                <CardMedia
                    sx={{ height: 300 }}
                    image={imageSrc}
                    title={name}
                />
                <CardContent>
                    <Typography variant="h5">{name}</Typography>
                    <Typography variant="body2">50% off on your first order</Typography>
                    <div className="py-2 space-y-2">
                        <p>{location}</p>
                        <p className="text-sm text-blue-500">{startDateTime}</p>
                        <p className="text-sm text-red-500">{endDateTime}</p>
                    </div>
                </CardContent>
                {/* Conditionally render the delete icon */}
                {false && (
                    <CardActions>
                        <IconButton>
                            <DeleteIcon />
                        </IconButton>
                    </CardActions>
                )}
            </Card>
        </div>
    );
};
