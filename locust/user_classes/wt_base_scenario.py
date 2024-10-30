from locust import task, SequentialTaskSet, HttpUser, constant_pacing, events
from config.config import cfg, logger


class PurchaseFlightTicket(SequentialTaskSet): # класс с задачами (содержит основной сценарий)
    @task
    def uc_00_getHomePage(self) -> None:

        r00_01_headers = { 
            'sec-fetch-mode': 'navigate'
        }

        r00_01_response = self.client.get(
            '/WebTours/',
            name="REQ_00_0_getHtml",
            allow_redirects=False,
            headers=r00_01_headers

            
        )
        print(r00_01_response.status_code)
        print(r00_01_response.text)
 

class  WebToursBaseUserClass(HttpUser): # юзер-класс, принимающий в себя основные параметры теста:
     wait_time = constant_pacing(cfg.pacing)

     host = cfg.url
     logger.info(f'WebToursBaseUserClass started. Host: {host}')
     tasks = [PurchaseFlightTicket]



    