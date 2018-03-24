package com.konaire.animatedstorecards.network

import com.konaire.animatedstorecards.model.Card
import com.konaire.animatedstorecards.model.ListData

/**
 * Created by Evgeny Eliseyev on 24/03/2018.
 */

class Mock {
    companion object {
        fun getListData(): ListData = ListData(
            "La Delice",
            "Pastry Shop",
            "https://flytothesky.ru/wp-content/uploads/2016/11/kak-vybrat-bokal-dlya-vina.jpg",
            arrayListOf(
                Card(
                    "http://www.sentidoorkalotusbeach.com/Uploaded/Content/565fb531-3896-4788-9619-bff348df9f71.jpg",
                    "About us",
                    "A restaurant, or an eatery, is a business which prepares and serves food and drinks to customers in exchange for money.",
                    "A restaurant, or an eatery, is a business which prepares and serves food and drinks to customers in exchange for money. Meals are generally served and eaten on the premises, but many restaurants also offer take-out and food delivery services, and some offer only take-out and delivery. Restaurants vary greatly in appearance and offerings, including a wide variety of cuisines and service models ranging from inexpensive fast food restaurants and cafeterias to mid-priced family restaurants, to high-priced luxury establishments. In Western countries, most mid- to high-range restaurants serve alcoholic beverages such as beer and wine. Some restaurants serve all the major meals, such as breakfast, lunch, and dinner (e.g., major fast food chains, diners, hotel restaurants, and airport restaurants). Other restaurants may only serve a single meal (e.g., a pancake house may only serve breakfast) or they may serve two meals (e.g., lunch and dinner) or even a kids' meal."
                ), Card(
                    "http://infoindustria.com.ua/wp-content/uploads/2016/08/Ovoshhi-i-fruktyi.jpg",
                    "Menu",
                    "Restaurants are classified or distinguished in many different ways. The primary factors are usually the food itself.",
                    "Restaurants are classified or distinguished in many different ways. The primary factors are usually the food itself (e.g. vegetarian, seafood, steak); the cuisine (e.g. Italian, Chinese, Japanese, Indian, French, Mexican, Thai) or the style of offering (e.g. tapas bar, a sushi train, a tastet restaurant, a buffet restaurant or a yum cha restaurant). Beyond this, restaurants may differentiate themselves on factors including speed (see fast food), formality, location, cost, service, or novelty themes (such as automated restaurants). Restaurants range from inexpensive and informal lunching or dining places catering to people working nearby, with modest food served in simple settings at low prices, to expensive establishments serving refined food and fine wines in a formal setting. In the former case, customers usually wear casual clothing. In the latter case, depending on culture and local traditions, customers might wear semi-casual, semi-formal or formal wear. Typically, at mid- to high-priced restaurants, customers sit at tables, their orders are taken by a waiter, who brings the food when it is ready. After eating, the customers then pay the bill. In some restaurants, such as workplace cafeterias, there are no waiters; the customers use trays, on which they place cold items that they select from a refrigerated container and hot items which they request from cooks, and then they pay a cashier before they sit down. Another restaurant approach which uses few waiters is the buffet restaurant. Customers serve food onto their own plates and then pay at the end of the meal. Buffet restaurants typically still have waiters to serve drinks and alcoholic beverages. Fast food restaurants are also considered a restaurant."
                ), Card(
                    "http://classpic.ru/wp-content/uploads/krasivo-oformlennyj-desert.jpg",
                    "Delivery",
                    "Restaurant guides review restaurants, often ranking them or providing information to guide consumers.",
                    "Restaurant guides review restaurants, often ranking them or providing information to guide consumers (type of food, handicap accessibility, facilities, etc.). One of the most famous contemporary guides is the Michelin series of guides which accord from 1 to 3 stars to restaurants they perceive to be of high culinary merit. Restaurants with stars in the Michelin guide are formal, expensive establishments; in general the more stars awarded, the higher the prices."
                )
            )
        )

    }
}