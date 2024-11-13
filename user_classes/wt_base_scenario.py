from locust import task, SequentialTaskSet, FastHttpUser, HttpUser, constant_pacing, events
from config.config import cfg, logger
from utils.assertion import check_http_response
from utils.non_test_methods import open_csv_file,generateFlightsDates,generateRandomCardNumber
import sys, re, random
from urllib.parse import quote_plus, quote



class PurchaseFlightTicket(SequentialTaskSet): # класс с задачами (содержит основной сценарий)

    test_users_csv_filepath = './test_data/user_creads.csv'
    test_flights_data_csv_filepth = './test_data/flight_detalis.csv'

    test_users_data = open_csv_file(test_users_csv_filepath)
    test_flights_data = open_csv_file(test_flights_data_csv_filepth)

    
 

    post_reqvest_content_type_headerr = {
        'Content-Type': 'application/x-www-form-urlencoded'
        }

    def on_start(self):
       
        @task
        def uc00_getHomePage(self) -> None:
          
            logger.info(f"Test data for users is: {self.test_users_data}")

            r00_00_headers = { 
                'sec-fetch-mode': 'navigate'
            }

            r00_00_response = self.client.get(
                '/WebTours/',
            name="req_00_0_Get_/WebTours/",
            allow_redirects=False
            
            
            
            )
        

            r00_0_response = self.client.get(
                '/webtours/',
            name="req_00_0_getHomePage_/webtours/",
            allow_redirects=False,
            #debug_stream=sys.stderr

            )
           
            r00_1_response = self.client.get(
                '/webtours/header.html',
            name="req_00_1_getHomePage_/webtours/header.html",
            allow_redirects=False,
            #debug_stream=sys.stderr

            )

    
            r00_2_response = self.client.get(
                '/cgi-bin/welcome.pl?signOff=true',
            name="req_00_2_gatHomePage_/cgi-bin/welcome.pl?signOff=true",
            allow_redirects=False,
            #debug_stream=sys.stderr

            )    


            with self.client.get(
                '/cgi-bin/nav.pl?in=home',
            name="req_00_3_GetHomePage_/cgi-bin/nav.pl?in=home",
            allow_redirects=False,
            catche_response=True
            #debug_stream=sys.stderr

            )   as req_00_3_response:
                check_http_response(req_00_3_response, 'name="userSession"' )
            #print(f"\ n__\n req_00_3_response.text:{req_00_3_response.text}\n__\n") 
            self.user_session = re.search(r" name=\"userSession\" value=\"(.*)\"/>",req_00_3_response.text).group(1)

            # logger.info(f"r_00_3_response_text:{req_00_3_response.text}")
            logger.info(f" USER_SESSION PARAMETER: {self.user_session}")
            

        
            r00_4_response = self.client.get(
                '/WebTours/home.html',
            name="req_00_4_GetHomePage_/WebTours/home.html",
            allow_redirects=False,
           #debug_stream=sys.stderr

            )  

        @task
        def uc01_LoginAction(self) -> None:
            

            self.users_data_row = random.choice(self.test_users_data)
            logger.info(self.users_data_row)

            self.username =self.users_data_row["Login"]
            self.password =self.users_data_row["password"]
            self.firstname =self.users_data_row["firstname"]
            self.lastname =self.users_data_row["lastname"]
            self.address1 =self.users_data_row["address1"]
            self.address2 =self.users_data_row["address2"]
            




            logger.info(f"chosen username: {self.username}/password: {self.password}")

            r01_00_body = f"userSession={self.user_session}&username={self.username}&password={self.password}&login.x=0&login.y=0&JSFormSubmit=off"

            with self.client.post(
                '/cgi-bin/login.pl',
            name='req_01_0_LoginAction_/cgi-bin/login.pl',
            headers=self.post_reqvest_content_type_headerr,
            data=r01_00_body,
            #debug_stream=sys.stderr,
            catch_response=True
            

            ) as req_01_00response:
                check_http_response(req_01_00response, "User password was correct") 


            with self.client.get(
                '/cgi-bin/nav.pl?page=menu&in=home',
            name='req_01_01_LoginAction_/cgi-bin/nav.pl?page=menu&in=home',
            #debug_stream=sys.stderr,
            catch_response=True

            )   as r_01_01response:
               check_http_response(r_01_01response, "Web Tours Navigation Bar") 


            with self.client.get(
                '/cgi-bin/login.pl?intro=true',
            name='req_01_02_LoginAction_/cgi-bin/login.pl?intro=true',
            #debug_stream=sys.stderr,
            catch_response=True

            )   as r_01_02response:
               check_http_response(r_01_02response, "Welcome to Web Tours") 
       
        uc00_getHomePage(self)
        uc01_LoginAction(self)

    @task
    def uc02_OpenFlightsTab(self):
       self.client.get(
            f'/cgi-bin/welcome.pl?page=search',
            name = "req_02_0_OpenFlightsTab_cgi-bin/welcome.pl?page=search",
            allow_redirects=False,
           #debug_stream=sys.stderr

       )


       self.client.get(
            f'/cgi-bin/nav.pl?page=menu&in=flights',
            name = "req_02_1_OpenFlightsTab_cgi-bin/nav.pl?page=menu&in=flights",
            allow_redirects=False,
            #debug_stream=sys.stderr

       )


       self.client.get(
            f'/cgi-bin/reservations.pl?page=welcome',
            name = "req_02_2_OpenFlightsTab_cgi-bin/reservations.pl?page=welcome",
            allow_redirects=False,
            #debug_stream=sys.stderr

       )

    @task
    def uc03_FindFlight_InputParams(self):
        self.flights_data_row = random.choice(self.test_flights_data)

        depart = self.users_data_row["depart"]
        arrive = self.users_data_row["arrive"]
        self.seat_pref = self.flights_data_row["seatPref"] 
        self.seat_type = self.flights_data_row["seatType"]
        self.expDate =self.flights_data_row["expDate"] 

        dates_dict = generateFlightsDates()
        

        
        r03_00_body = f"advanceDiscount=0&depart={depart}&departDate={dates_dict["depart_date"]}&arrive={arrive}&returnDate={dates_dict["return_date"]}&numPassengers=1&seatPref={self.seat_pref}&seatType={self.seat_type}&findFlights.x=54&findFlights.y=11&.cgifields=roundtrip&.cgifields=seatType&.cgifields=seatPref"
        logger.info(f"uc03 reqvest body: {r03_00_body}")

        with self.client.post(
            '/cgi-bin/reservations.pl',
            name='req_03_0_FindFlight_InputParams_/cgi-bin/reservations.pl',
            headers=self.post_reqvest_content_type_headerr,
            data=r03_00_body,
            #debug_stream=sys.stderr,
            catch_response=True
            

        ) as r_03_0response:
            check_http_response(r_03_0response, "Flight departing from")
            self.outboundFlight = re.search(r"name=\"outboundFlight\" value=\"(.*)\" checked=\"checked\"", r_03_0response.text).group(1)

     
    @task
    def uc04_ChooseFlightOption(self):
        
        
        r04_00_body = f"outboundFlight={quote(self.outboundFlight)}&numPassengers=1&advanceDiscount=0&seatType={self.seat_type}&seatPref={self.seat_pref}&reserveFlights.x=60&reserveFlights.y=2"
        logger.info(f"uc04 reqvest body: {r04_00_body}")

        with self.client.post(
            '/cgi-bin/reservations.pl',
            name='req_04_0_ChooseFlightOption_/cgi-bin/reservations.pl',
            headers=self.post_reqvest_content_type_headerr,
            data=r04_00_body,
            #debug_stream=sys.stderr,
            catch_response=True
            

        ) as r_04_0response:
            
            check_http_response(r_04_0response, "Total for 1 ticket(s) is =")        


    @task
    def uc05_ConfirmFlightBooking(self):
        
        
        r05_00_body = f"firstName={self.firstname}&lastName={self.lastname}&address1={quote(self.address1)}&address2={quote(self.address2)}&pass1={quote(self.firstname+ ' ' +self.lastname)}&creditCard={generateRandomCardNumber()}&expDate={quote(self.expDate)}&numPassengers=1&seatType={self.seat_type}&seatPref={self.seat_pref}&outboundFlight={quote(self.outboundFlight)}&advanceDiscount=0&returnFlight=&JSFormSubmit=off&buyFlights.x=68&buyFlights.y=16&.cgifields=saveCC"
        logger.info(f"uc04 reqvest body: {r05_00_body}")

        with self.client.post(
            '/cgi-bin/reservations.pl',
            name='req_05_0_ConfirmFlightBooking_/cgi-bin/reservations.pl',
            headers=self.post_reqvest_content_type_headerr,
            data=r05_00_body,
            debug_stream=sys.stderr,
            catch_response=True
            

        ) as r_05_0response:
        
            
            check_http_response(r_05_0response, "Credit Account Balance")        
 
           






class  WebToursBaseUserClass(FastHttpUser): # юзер-класс, принимающий в себя основные параметры теста:_
     wait_time = constant_pacing(cfg.webtours_base.pacing)

     host = cfg.url
     logger.info(f'WebToursBaseUserClass started. Host: {host}')
     tasks = [PurchaseFlightTicket]



    