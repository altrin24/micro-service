package com.cricket.simulator.matches.service;

import com.cricket.simulator.matches.dto.FixtureCreateDto;
import com.cricket.simulator.matches.dto.MatchResDto;
import com.cricket.simulator.matches.mapper.MatchMapper;
import com.cricket.simulator.matches.repository.FixtureRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FixtureServiceImpl implements FixtureService {

    private final FixtureRepo fixtureRepo;
    private final MatchMapper matchMapper;

    @Override
    public MatchResDto saveFixtures(FixtureCreateDto fixtureCreateDto) {
        return MatchResDto.builder().
                id(fixtureRepo.save(matchMapper.DtoToEntity(fixtureCreateDto)).getFixId()).
                msg("created").build();
    }
}
