import React from "react";
import { EventCard } from "./EventCard";
import { useSelector } from "react-redux";

export const Events=()=>{
    const { restaurant } = useSelector((store) => store);
    return (
        <div className="mt-5 px-5 flex flex-wrap gap-5">

            {restaurant.restaurantsEvents?.map((item) => (
                       <EventCard key={item.id} item={item} />
                     ))}
           
        </div>
    )
}