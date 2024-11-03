from locust import task, SequentialTaskSet, FastHttpUser, HttpUser, constant_pacing, events
from config.config import cfg, logger
from utils.assertion import check_http_response
from utils.non_test_methods import open_csv_file
import sys, re, random

class PurchaseFlightTicket(SequentialTaskSet): # класс с задачами (содержит основной сценарий)

    test_users_csv_filepath = './test_data/user_creads.csv'

    def on_start(self):
       
        @task
        def uc00_getHomePage(self) -> None:
            self.test_users_data = open_csv_file(self.test_users_csv_filepath)
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
            r01_00_headers = {
                'Content-Type': 'application/x-www-form-urlencoded'

            }

            self.users_data_row = random.choice(self.test_users_data)
            logger.info(self.users_data_row)

            self.username =self.users_data_row["Login"]
            self.password =self.users_data_row["password"]

            logger.info(f"chosen username: {self.username}/password: {self.password}")

            r01_00_body = f"userSession={self.user_session}&username={self.username}&password={self.password}&login.x=0&login.y=0&JSFormSubmit=off"

            with self.client.post(
                '/cgi-bin/login.pl',
            name='req_01_0_LoginAction_/cgi-bin/login.pl',
            headers=r01_00_headers,
            data=r01_00_body,
            debug_stream=sys.stderr,
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

            )   as r_01_02respovse:
               check_http_response(r_01_02respovse, "Welcome to Web Tours") 
       
        uc00_getHomePage(self)
        uc01_LoginAction(self)

    @task
    def mydummY_task(self):
         print ('что-либо')




                
           





class  WebToursBaseUserClass(FastHttpUser): # юзер-класс, принимающий в себя основные параметры теста:
     wait_time = constant_pacing(cfg.pacing)

     host = cfg.url
     logger.info(f'WebToursBaseUserClass started. Host: {host}')
     tasks = [PurchaseFlightTicket]

