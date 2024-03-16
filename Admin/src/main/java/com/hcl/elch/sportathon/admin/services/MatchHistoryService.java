package com.hcl.elch.sportathon.admin.services;

import com.hcl.elch.sportathon.admin.customException.WrongValueException;
import com.hcl.elch.sportathon.admin.entities.MatchDetails;
import com.hcl.elch.sportathon.admin.entities.MatchResult;
import com.hcl.elch.sportathon.admin.pojo.MatchHistoryPojo;
import com.hcl.elch.sportathon.admin.repositories.MatchDetailRepository;
import com.hcl.elch.sportathon.admin.repositories.MatchResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchHistoryService {

    @Autowired
    private MatchDetailRepository matchDetailRepository;

    @Autowired
    private MatchResultRepository matchResultRepository;

    public List<MatchHistoryPojo> getMatchHistoryWithResults(Long playerId) {
        List<MatchDetails> matches = this.matchDetailRepository.findAll().stream()
                .filter(match -> match.getTeamId1().equals(playerId) || match.getTeamId2().equals(playerId))
                .collect(Collectors.toList());
        List<MatchHistoryPojo> matchHistories = new ArrayList<>();
        for (MatchDetails match : matches) {
            Optional<MatchResult> resultOpt = matchResultRepository.findById(match.getMatchID());
            if (resultOpt.isPresent()) {
                matchHistories.add(new MatchHistoryPojo(match, resultOpt.get()));
            } else {
                matchHistories.add(new MatchHistoryPojo(match, null));
            }
        }
        return matchHistories;
    }

}
