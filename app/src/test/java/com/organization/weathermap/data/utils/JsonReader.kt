package com.organization.weathermap.data.utils

object JsonReader {
    fun getJson(path: String): String =
        "{" +
                "  \"status\": \"OK\"," +
                "  \"copyright\": \"Copyright (c) 2022 The New York Times Company.  All Rights Reserved.\"," +
                "  \"num_results\": 20," +
                "  \"results\": [" +
                "    {" +
                "      \"uri\": \"nyt://article/6b915b80-14bb-5063-b0ec-2505eb55fc0d\"," +
                "      \"url\": \"https://www.nytimes.com/2022/09/14/climate/patagonia-climate-philanthropy-chouinard.html\"," +
                "      \"id\": 100000008485792," +
                "      \"asset_id\": 100000008485792," +
                "      \"source\": \"New York Times\"," +
                "      \"published_date\": \"2022-09-14\"," +
                "      \"updated\": \"2022-09-21 18:05:13\"," +
                "      \"section\": \"Climate\"," +
                "      \"subsection\": \"\"," +
                "      \"nytdsection\": \"climate\"," +
                "      \"adx_keywords\": \"Global Warming;Philanthropy;Corporate Social Responsibility;High Net Worth Individuals;Capitalism (Theory and Philosophy);Content Type: Personal Profile;Fashion and Apparel;Mergers, Acquisitions and Divestitures;Chouinard, Yvon;Patagonia Inc\"," +
                "      \"column\": null," +
                "      \"byline\": \"By David Gelles\"," +
                "      \"type\": \"Article\"," +
                "      \"title\": \"Billionaire No More: Patagonia Founder Gives Away the Company\"," +
                "      \"abstract\": \"Yvon Chouinard has forfeited ownership of the company he founded 49 years ago. The profits will now be used to fight climate change.\"," +
                "      \"des_facet\": [" +
                "        \"Global Warming\"," +
                "        \"Philanthropy\"," +
                "        \"Corporate Social Responsibility\"," +
                "        \"High Net Worth Individuals\"," +
                "        \"Capitalism (Theory and Philosophy)\"," +
                "        \"Content Type: Personal Profile\"," +
                "        \"Fashion and Apparel\"," +
                "        \"Mergers, Acquisitions and Divestitures\"" +
                "      ]," +
                "      \"org_facet\": [" +
                "        \"Patagonia Inc\"" +
                "      ]," +
                "      \"per_facet\": [" +
                "        \"Chouinard, Yvon\"" +
                "      ]," +
                "      \"geo_facet\": []," +
                "      \"media\": [" +
                "        {" +
                "          \"type\": \"image\"," +
                "          \"subtype\": \"photo\"," +
                "          \"caption\": \"\"," +
                "          \"copyright\": \"Natalie Behring for The New York Times\"," +
                "          \"approved_for_syndication\": 1," +
                "          \"media-metadata\": [" +
                "            {" +
                "              \"url\": \"https://static01.nyt.com/images/2022/09/12/climate/00cli-patagonia-promo/00cli-patagonia-promo-thumbStandard.jpg\"," +
                "              \"format\": \"Standard Thumbnail\"," +
                "              \"height\": 75," +
                "              \"width\": 75" +
                "            }," +
                "            {" +
                "              \"url\": \"https://static01.nyt.com/images/2022/09/12/climate/00cli-patagonia-promo/00cli-patagonia-promo-mediumThreeByTwo210.jpg\"," +
                "              \"format\": \"mediumThreeByTwo210\"," +
                "              \"height\": 140," +
                "              \"width\": 210" +
                "            }," +
                "            {" +
                "              \"url\": \"https://static01.nyt.com/images/2022/09/12/climate/00cli-patagonia-promo/00cli-patagonia-promo-mediumThreeByTwo440.jpg\"," +
                "              \"format\": \"mediumThreeByTwo440\"," +
                "              \"height\": 293," +
                "              \"width\": 440" +
                "            }" +
                "          ]" +
                "        }" +
                "      ]," +
                "      \"eta_id\": 0" +
                "    }" +
                "  ]" +
                "}"
}
