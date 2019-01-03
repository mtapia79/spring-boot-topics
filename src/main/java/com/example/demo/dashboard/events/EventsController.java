package com.example.demo.dashboard.events;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dashboard.github.GithubClient;
import com.example.demo.dashboard.github.RepositoryEvent;

@Controller
public class EventsController {

    private final GithubClient githubClient;

    private final GithubProjectRepository repository;
    
    @Autowired
    EventRepository eventRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    RestTemplate restTemplate;
    @Value("${operations.restURL}")
    String serviceURL;
    

    public EventsController(GithubClient githubClient, GithubProjectRepository repository) {
        this.githubClient = githubClient;
        this.repository = repository;
    }
    
    @RequestMapping(value = "/eventos", method = RequestMethod.POST)
    public @ResponseBody Event interceptorExample(@RequestBody Event event) {
    
        return event;
    }
    
    @PostMapping("/events")
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
       
       Event savedEvent =  this.eventRepository.save(event);
       
       if (savedEvent == null) {
            return ResponseEntity.badRequest().build();
        }
      
       //return new ResponseEntity<Event>(savedEvent, HttpStatus.FOUND);
       
       return ResponseEntity.status(HttpStatus.CREATED)
        .header("", "")
        .body("");
    }
    
    @DeleteMapping("/erase")
    public  ResponseEntity<String> erase() {
        this.eventRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
 
    }
    
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEvents() {
       List<Event> listEvent =  this.eventRepository.findAll();
       return new ResponseEntity<List<Event>>(listEvent, HttpStatus.OK);
    }
    
    @GetMapping("/events/actors/{actorID}")
    public ResponseEntity<List<Event>> getEventsByActorId(@PathVariable Long actorID) {
       List<Event> actorEvents =  this.eventRepository.findByActorId(actorID);
       
       if (actorEvents == null) {
            return ResponseEntity.notFound().build();
        }
        
       return new ResponseEntity<List<Event>>(actorEvents, HttpStatus.OK);
    }
    
    

    @GetMapping("/events/{repoName}")
    @ResponseBody
    public RepositoryEvent[] fetchEvents(@PathVariable String repoName) {
        GithubProject project = this.repository.findByRepoName(repoName);
  
        return this.githubClient.fetchEvents(project.getOrgName(), project.getRepoName()).getBody();
        /*if (project == null) {
            return ResponseEntity.notFound().build();
        }
        ResponseEntity<RepositoryEvent[]> response = this.githubClient
                .fetchEvents(project.getOrgName(), project.getRepoName());
        return ResponseEntity.ok()
                .eTag(response.getHeaders().getETag())
                .body(response.getBody());*/
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        List<DashboardEntry> entries = StreamSupport
                .stream(this.repository.findAll().spliterator(), true)
                .map(p -> new DashboardEntry(p, githubClient.fetchEventsList(p.getOrgName(), p.getRepoName())))
                .collect(Collectors.toList());
        model.addAttribute("entries", entries);
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("projects", repository.findAll());
        return "admin";
    }
    
    
   /* @GetMapping("/availability")
    public String getAvailability() {
        return restTemplate.getForEntity(serviceURL, new Object());
    }*/
    
    @Bean
    public RestTemplate rest() {
    return new RestTemplate();
    }

}
