package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.ReleaseRepository;
import com.example.backdsdm.entities.Release;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReleaseServiceImpl implements IReleaseService {

    private final ReleaseRepository releaseRepository;

    @Override
    public List<Release> getReleasesByDeploymentPlanId(String deploymentPlanId) {
        return releaseRepository.findByDeploymentPlanId(deploymentPlanId);
    }

    @Override
    public Release addRelease(String deploymentPlanId, String name, String details) {
        Release release = new Release();
        release.setDeploymentPlanId(deploymentPlanId);
        release.setName(name);
        release.setDetails(details);
        return releaseRepository.save(release);
    }
}
