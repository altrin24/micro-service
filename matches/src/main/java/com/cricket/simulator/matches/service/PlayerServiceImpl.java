package com.cricket.simulator.matches.service;

import com.cricket.simulator.matches.dto.PlayerResDto;
import com.cricket.simulator.matches.entity.MatchDetails;
import com.cricket.simulator.matches.exception.MatchDetailsNotFound;
import com.cricket.simulator.matches.repository.MatchDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements  PlayerService{

    private final MatchDetailsRepo matchDetailsRepo;


    @Override
    public Map<Long, Integer> getBestPlayer() throws MatchDetailsNotFound {
       return  Stream.of(matchDetailsRepo.findAll().stream().
                collect(Collectors.groupingBy(MatchDetails::getBattingPlayerId, Collectors.summingInt(MatchDetails::getRun))).entrySet()
                .stream().max(Map.Entry.comparingByValue()).orElseThrow(MatchDetailsNotFound::new)).collect(Collectors.toMap(e->e.getKey(),v->v.getValue()));

    }

    @Override
    public List<PlayerResDto> getBestBowler() {
        return List.of();
    }

    public <K, V> Map<K, V> entrySetToMap(Set<Map.Entry<K, V>> entrySet) {
        return entrySet.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
